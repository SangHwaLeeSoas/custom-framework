package com.moin.api.domain.transfer.service

import com.fin.best.bestfin.api.component.constants.AppConst
import com.fin.best.bestfin.api.component.constants.AppConst.Transfer.FeePolicy.Companion.getFeeDTO
import com.moin.api.component.exception.CommonException
import com.moin.api.component.util.BigDecimalUtil
import com.moin.api.component.util.CurrencyUtil
import com.moin.api.component.util.UserDetailsUtil
import com.moin.api.domain.transfer.entity.Quote
import com.moin.api.domain.transfer.model.ExchangeDTO
import com.moin.api.domain.transfer.model.QuoteRequestDTO
import com.moin.api.domain.transfer.repository.QuoteRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime
import java.util.*

@Service
class TransferService(
    private val quoteRepository: QuoteRepository,
    private val exchangeService: ExchangeService,
    private val userDetailsUtil: UserDetailsUtil,
) {

    private val logger = LoggerFactory.getLogger(TransferService::class.java)


    /* 견적서 생성 */
    @Transactional
    @Throws(CommonException::class)
    fun makeQuote(quoteRequestDTO: QuoteRequestDTO): Map<String, Any?> {

        val user = userDetailsUtil.getUserDetails()

        /* 환율 정보 조회 */
        val exchangeInfo = exchangeService.getExchangeInfo(getExchnageCode(quoteRequestDTO.targetCurrency))
            ?: throw CommonException(AppConst.ResCode.EXCHANGE_SERVER_ERROR)
        logger.info("exchangeInfo: $exchangeInfo")

        /* 수수료 정책 조회 */
        val feeDTO =
            getFeeDTO(AppConst.Transfer.Currency.valueOf(quoteRequestDTO.targetCurrency), quoteRequestDTO.amount)
                ?: throw CommonException(AppConst.ResCode.FEE_POLICY_NOT_FOUND)
        logger.info("feeDTO: $feeDTO")

        /* 총 수수료*/
        val totalFee = calcTotalFee(quoteRequestDTO, feeDTO)
        /* 수신 금액 */
        val receiveAmount = calcReceiveAmount(exchangeInfo, quoteRequestDTO, totalFee)

        /* 수수료 이하의 금액일 때 송금불가 */
        if (BigDecimalUtil.isZero(receiveAmount))
            throw CommonException(AppConst.ResCode.NOT_ENOUGH_SOURCE_AMOUNT)

        /* 견적서 생성 */
        val quote = Quote(
            userIdx = user.userIdx,
            targetCurrency = quoteRequestDTO.targetCurrency,
            exchangeRate = exchangeInfo.basePrice,
            currencyUnit = exchangeInfo.currencyUnit,
            fiexedFee = feeDTO.fixedFee,
            feeRate = feeDTO.feeRate,
            totalFee = totalFee,
            sourceAmount = quoteRequestDTO.amount,
            targetAmount = receiveAmount,
            expireTime = LocalDateTime.now().plusMinutes(AppConst.Transfer.QUOTE_EXPIRE_MIN),
        )

        val result = quoteRepository.save(quote)
        return mapOf("quote" to mapOf(
            "quoteId" to result.quoteIdx,
            "exchangeRate" to result.exchangeRate,
            "expireTime" to result.expireTime,
            "targetAmount" to result.targetAmount,
        ))
    }


    /* 송금 요청 */
    fun requestTransfer() {
        logger.info("requestTransfer !!!")
    }


    /* 화폐로 환율 코드 조회 */
    @Throws(CommonException::class)
    private fun getExchnageCode(currency: String): String {
        return when (currency) {
            AppConst.Transfer.Currency.USD.code -> {
                AppConst.Transfer.ExchangeCode.USD.code
            }
            AppConst.Transfer.Currency.JPY.code -> {
                AppConst.Transfer.ExchangeCode.JPY.code
            }
            else -> {
                logger.error("getExchnageCode currency error $currency")
                throw CommonException(AppConst.ResCode.BAD_REQUEST)
            }
        }
    }


    /* 수수료 계산 */
    private fun calcTotalFee(quoteRequestDTO: QuoteRequestDTO, feeDTO: AppConst.Transfer.FeeDTO): BigDecimal {
        /* 수수료는 원화 */
        val feeDigits = CurrencyUtil.getDefaultFractionDigits(AppConst.Transfer.Currency.KRW.code)
        val amount = quoteRequestDTO.amount.toBigDecimal()
        val feeRate = feeDTO.feeRate
        val fixedFee = feeDTO.fixedFee.toBigDecimal()

        val totalFee = amount
            .multiply(feeRate)
            .add(fixedFee)
            .setScale(feeDigits, RoundingMode.HALF_UP)
        logger.info("totalFee: $totalFee")

        return totalFee
    }

    /* 수신 금액 계산 */
    private fun calcReceiveAmount(exchangeDTO: ExchangeDTO, quoteRequestDTO: QuoteRequestDTO, totalFee: BigDecimal ): BigDecimal {
        /* 받는 금액은 사용자 입력한 화폐 단위 */
        val receiveDigits = CurrencyUtil.getDefaultFractionDigits(quoteRequestDTO.targetCurrency)
        val basePrice = exchangeDTO.basePrice
        val currencyUnit = exchangeDTO.currencyUnit.toBigDecimal()
        val amount = quoteRequestDTO.amount.toBigDecimal()
        val receiveAmount = amount
            .subtract(totalFee)
            .divide(basePrice, AppConst.Transfer.MAX_DIGITS, RoundingMode.HALF_UP)
            .multiply(currencyUnit)
            .setScale(receiveDigits, RoundingMode.HALF_UP)
        logger.info("receiveAmount: $receiveAmount")

        return receiveAmount
    }
}