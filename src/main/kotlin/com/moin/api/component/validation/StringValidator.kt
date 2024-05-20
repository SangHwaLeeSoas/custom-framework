package com.moin.api.component.validation

import com.moin.api.component.constants.AppRegexp.User.BUSINESS_REGISTRATION_NUMBER
import com.moin.api.component.constants.AppRegexp.User.RESIDENT_REGISTRATION_NUMBER

object StringValidator {

    /* 주민등록번호 유효성 유무 */
    fun isValidResidentRegistrationNumber(value: String): Boolean {
        if (value.length != 14) return false
        return Regex(RESIDENT_REGISTRATION_NUMBER).matches(value)
    }

    /* 사업자등록번호 유효성 유무 */
    fun isValidBusinessRegistrationNumber(value: String): Boolean {
        if (value.length != 14) return false
        return Regex(BUSINESS_REGISTRATION_NUMBER).matches(value)
    }

}