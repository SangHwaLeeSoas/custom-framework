package com.moin.api.domain.transfer.service

import com.fin.best.bestfin.api.component.constants.AppConst
import com.fin.best.bestfin.api.component.constants.AppConst.Transfer.FeePolicy.Companion.getFeeDTO
import com.moin.api.component.exception.CommonException
import com.moin.api.component.util.BigDecimalUtil
import com.moin.api.component.util.CurrencyUtil
import com.moin.api.component.util.UserDetailsUtil
import com.moin.api.domain.transfer.entity.Quote
import com.moin.api.domain.transfer.model.*
import com.moin.api.domain.transfer.repository.QuoteRepository
import com.moin.api.domain.user.entity.User
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
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
    @Transactional
    @Throws(CommonException::class)
    fun requestTransfer(transferRequestDTO: TransferRequestDTO) {

        val user = userDetailsUtil.getUserDetails()

        val quote = quoteRepository.findById(transferRequestDTO.quoteId)
            .orElseThrow { CommonException(AppConst.ResCode.NOT_FOUND_QUOTE) }

        /* 만료일시 검증 */
        if (quote.expireTime.isBefore(LocalDateTime.now()))
            throw CommonException(AppConst.ResCode.EXPIRED_QUOTE)

        /* 대기 상태에서만 변경 가능 */
        if (quote.statusCode != AppConst.Transfer.StatusCode.WAIT.code)
            throw CommonException(AppConst.ResCode.INVALID_QUOTE)


        /* 일일 송금 제한 검증 */
//        checkDailyLimitSourceAmount(user, quote.sourceAmount)

        quote.statusCode = AppConst.Transfer.StatusCode.COMPLETE.code
        quote.requestedDate = LocalDateTime.now()
    }


    @Transactional(readOnly = true)
    fun getQuoteList(): Map<String,Any?> {
        val user = userDetailsUtil.getUserDetails()
        val quoteList = quoteRepository.findQuoteList(user.userIdx)
        val quoteSummaryDTO = getDailyTransferSummary(user.userIdx)

        return mapOf(
            "history" to quoteList,
            "todayTransferUsdAmount" to quoteSummaryDTO.totalSourceAmount,
            "todayTransferCount" to quoteSummaryDTO.totalTransferCount,
            "name" to user.name,
            "userId" to user.userId,
        )
    }


    /* 일일 송금 제한 검증 */
    fun checkDailyLimitSourceAmount(user: User, amount: Long) {
        /* TODO : 송금 화폐는 원화인데, 송금 제한은 USD로 설정 되어있음.
        *  환율을 적용하여 USD로 변환하여 계산해야 하는데, 환율에 대한 기준이 하루동안 일정하지 않으므로 추가적인 정책이 필요.
        * */

//        val dailyTotalSourceAmount = getDailyTotalSourceAmount(user.userIdx)

//        val dailyLimit = AppConst.Transfer.DAILY_LIMIT_SOURCE_AMOUNT
//        if (dailyTotalSourceAmount + amount > dailyLimit)
//            throw CommonException(AppConst.ResCode.DAILY_LIMIT_SOURCE_AMOUNT)
    }


    /* 일일 송금 합계 조회 */
    @Transactional(readOnly = true)
    fun getDailyTransferSummary(userIdx: Long): DailyTransferSummaryProjection {
        val today = LocalDate.now()
        val statusCode = AppConst.Transfer.StatusCode.COMPLETE.code
        return quoteRepository.findDailyTransferSummary(statusCode, today, userIdx)
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