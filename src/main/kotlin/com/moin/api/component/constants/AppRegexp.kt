package com.moin.api.component.constants

object AppRegexp {

    object User {
        // 아이디: 영문 숫자 혼용 5자이상 20자 이내
        const val USER_ID = "^[a-zA-Z]([a-zA-Z0-9]){4,19}$"
        // 비밀번호(영문, 숫자 혼용 8~20자 이내)
        // ㄴ 비밀번호 최소 하나의 문자 + 하나의 숫자 포함, 최소 8자리, 최대 20자리
        const val PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d).{8,20}$"
        /* 이메일 */
        const val EMAIL = "^[a-zA-Z0-9][a-zA-Z0-9_.-]+@[a-zA-Z.]+?\\.[a-zA-Z]{2,3}"
        /* 주민등록번호 */
//        const val RESIDENT_REGISTRATION_NUMBER = "^\\d{6}-?[1-4]\\d{6}$"
        const val RESIDENT_REGISTRATION_NUMBER = "^\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])-\\d{7}$"
        /* 사업자등록번호 */
        const val BUSINESS_REGISTRATION_NUMBER = "^\\d{3}-?\\d{2}-?\\d{5}$"


    }

}