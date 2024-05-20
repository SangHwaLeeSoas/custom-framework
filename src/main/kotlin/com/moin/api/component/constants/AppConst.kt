package com.fin.best.bestfin.api.component.constants

import org.springframework.http.HttpStatus
import java.math.BigDecimal

/**
 * ConstantCode 정의 외에 기본적으로 필요한 상수값들을 정의
 */
object AppConst {

    object User {
        enum class Role(val code: String, val description: String) {
            USER("USER", "사용자"),
        }

        enum class IdType(val code: String, val description: String) {
            REG_NO("REG_NO", "주민등록번호"),
            BUSINESS_NO("BUSINESS_NO ", "사업자등록번호"),
        }
    }


    object Transfer {

        /* 최대 자리수 */
        const val MAX_DIGITS = 12
        /* 견적서 만료 시간 (분) */
        const val QUOTE_EXPIRE_MIN = 10L

        enum class Currency(val code: String, val description: String) {
            KRW("KRW", "원화"),
            USD("USD", "미화"),
            JPY("JPY", "엔화"),
        }

        enum class TargetCurrency(val code: String, val description: String) {
            USD("USD", "미화"),
            JPY("JPY", "엔화"),
        }

        enum class ExchangeCode(val code: String, val description: String) {
            USD("FRX.KRWUSD", "미화"),
            JPY("FRX.KRWJPY", "엔화"),
        }

        enum class Limit(val code: String, val description: String) {
            USD_INDIVIDUAL("1000", "미화"),
            USD_BUSINESS("5000", "엔화"),
        }

        enum class StatusCode(val code: String, val description: String) {
            WAIT("WAIT", "대기"),
            COMPLETE("COMPLETE", "완료"),
            CANCEL("CANCEL", "취소"),
        }

        /* TODO : 추후 계산식과 함께 분리 필요 */
        data class FeeDTO(
            val minAmount: Long,
            val maxAmount: Long?,
            val fixedFee: Long,
            val feeRate: BigDecimal
        )

        /* TODO : DB 관리 시 개선 필요 */
        enum class FeePolicy(val currency: Currency, val feeDTO: FeeDTO) {
            USD_LOW(
                Currency.USD,
                FeeDTO(
                    minAmount = 1L,
                    maxAmount = 1000000L,
                    fixedFee = 1000L,
                    feeRate = BigDecimal("0.002")
                )
            ),
            USD_HIGH(
                Currency.USD,
                FeeDTO(
                    minAmount = 1000001L,
                    maxAmount = null,
                    fixedFee = 3000L,
                    feeRate = BigDecimal("0.001")
                )
            ),
            JPY_ALL(
                Currency.JPY,
                FeeDTO(
                    minAmount = 1L,
                    maxAmount = null,
                    fixedFee = 3000L,
                    feeRate = BigDecimal("0.005")
                )
            );

            companion object {
                fun getFeeDTO(currency: Currency, amount: Long): FeeDTO? {
                    return values().firstOrNull {
                        it.currency == currency &&
                                (it.feeDTO.maxAmount == null || amount <= it.feeDTO.maxAmount) &&
                                amount >= it.feeDTO.minAmount
                    }?.feeDTO
                }
            }
        }
    }


    enum class ResCode(val httpStatus: HttpStatus, val detail: String) {

        /* 공통 */
        OK(HttpStatus.OK, "OK"),
        BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 파라미터입니다."),
        UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "사용할 수 없는 토큰입니다."),
        INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 에러 입니다."),

        /* 회원 */
        DUPLICATED_USER_ID(HttpStatus.BAD_REQUEST, "중복된 아이디입니다."),
        NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "회원 정보를 찾을 수 없습니다."),
        BAD_CREDENTIALS(HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호가 일치하지 않습니다."),

        /* 송금 */
        EXCHANGE_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "환율 정보 조회를 실패했습니다."),
        FEE_POLICY_NOT_FOUND(HttpStatus.BAD_REQUEST, "수수료 정책 조회를 실패했습니다."),
        NOT_ENOUGH_SOURCE_AMOUNT(HttpStatus.BAD_REQUEST, "너무 적은 송금 금액입니다."),
    }

}
