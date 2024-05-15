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

        /****************************** 공통 ******************************/
        OK(HttpStatus.OK, "성공"),

        REQUIRED_DATA_PARAMETER(HttpStatus.BAD_REQUEST, "필수 파라미터 누락"),
        INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "유효하지 않은 파라미터"),
        INVALID_DATA_TYPE(HttpStatus.BAD_REQUEST, "유효하지 않은 데이터 타입"),
        INVALID_YN_TYPE(HttpStatus.BAD_REQUEST, "유효한 YN 아님"),
        INVALID_CONSTANT_TYPE(HttpStatus.BAD_REQUEST, "유효하지 않은 상수 데이터"),
        INVALID_REGEX_TYPE(HttpStatus.BAD_REQUEST, "형식에 맞지 않는 데이터"),
        NOT_FOUND_DATA(HttpStatus.NOT_FOUND, "조회 데이터 없음"),
        INTERVAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 장애"),
        INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 access token"),
        NO_CERT_ACCOUNT(HttpStatus.BAD_REQUEST, "본인인증되지 않은 계정입니다."),
        LIMIT_MINIMUM_AGE(HttpStatus.UNAUTHORIZED, "19세 미만 미성년자는 서비스 사용이 불가합니다."),
        NOT_FOUND_LOGIN_INFO(HttpStatus.UNAUTHORIZED, "로그인 정보를 찾을 수 없습니다."),
        NOT_FOUND_COMPANY_INFO(HttpStatus.NOT_FOUND, "업체 정보를 찾을 수 없습니다."),
        SEND_EMAIL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "이메일 전송에 실패하였습니다."),
        INVALID_REASON_TYPE(HttpStatus.BAD_REQUEST, "잘못된 사유입니다."),
        NOT_FOUND_SECURE_KEY(HttpStatus.BAD_REQUEST, "키를 찾을 수 없습니다."),
        EXPIRED_SECURE_KEY(HttpStatus.BAD_REQUEST, "만료된 키입니다."),
        INACTIVATED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "이미 다른 기기 및 경로에서 로그인 되어있습니다.\n다른 기기 및 경로에서 로그아웃 후 로그인 해주세요."),
        /****************************** 공통 ******************************/

    }

}
