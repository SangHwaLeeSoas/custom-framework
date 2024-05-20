package com.fin.best.bestfin.api.component.constants

import org.springframework.http.HttpStatus

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


    object DateFormat {
        const val YearMonthHourMinSecFormat = "yyyyMMddHHmmss"
        const val YearMonthHourMinSecDashFormat = "yyyy-MM-dd HH:mm:ss"
        const val YearMonthDateFormat = "yyyyMMdd"
        const val YearMonthHourDotFormat = "yyyy.MM.dd"
        const val YearMonthHourDashFormat = "yyyy-MM-dd"
        const val YearMonthDashFormat = "yyyy-MM"
        const val YearMonthFormat = "yyyyMM"
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

    }

}
