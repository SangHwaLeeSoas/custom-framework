package com.fin.best.bestfin.api.component.constants

object AppRegexp {
    object Auth {
        const val PlatformType = "^(BROWSER|IOS|ANDROID)$"
        const val PinType = "^(FIND_ID|FIND_PW|JOIN|RE_JOIN|CHANGE_MOBILE)$"
        const val UserType = "^(USER|AGENT|COUNSELOR)$"
        const val MobileNumber = "^[0][1-9]\\d{8,9}$"
    }

    object Cert {
        const val Gender = "^(M|F)$"
        const val Birth = "([12]\\d{3}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01]))"
    }

    object User {
        // 아이디: 영문 숫자 혼용 5자이상 20자 이내
        const val UserId = "^[a-zA-Z]([a-zA-Z0-9]){4,19}$"
        // 비밀번호(영문, 숫자 혼용 8~20자 이내)
        // ㄴ 비밀번호 최소 하나의 문자 + 하나의 숫자 포함, 최소 8자리, 최대 20자리
        const val Password = "^(?=.*[A-Za-z])(?=.*\\d).{8,20}$"
        // 이메일: ccdev@codecraft.co.kr
        const val Email = "^[a-zA-Z0-9][a-zA-Z0-9_.-]+@[a-zA-Z.]+?\\.[a-zA-Z]{2,3}"
    }

    object CounselorUser {
        // 비밀번호
        // ㄴ 8~20자 영문 대문자, 소문자, 숫자, 특수문자 중 3종류 조합
        const val Password = "^((?=.*[a-z])(?=.*[0-9])(?=.*[\$`~!@$!%*#^?&\\(\\)\\-_=+])|(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])|(?=.*[a-z])(?=.*[A-Z])(?=.*[\$`~!@\$!%*#^?&\\\\(\\\\)\\-_=+])|(?=.*[A-Z])(?=.*[0-9])(?=.*[\$`~!@\$!%*#^?&\\\\(\\\\)\\-_=+])).{8,20}\$"
    }

    object Agent {
        // 아이디: 영문 숫자 혼용 5자이상 20자 이내
        const val AgentId = "^[a-zA-Z]([a-zA-Z0-9]){4,19}$"
        // 비밀번호(영문, 숫자 혼용 8~20자 이내)
        // ㄴ 비밀번호 최소 하나의 문자 + 하나의 숫자 포함, 최소 8자리, 최대 20자리
        const val Password = "^(?=.*[A-Za-z])(?=.*\\d).{8,20}$"
        // 이메일: ccdev@codecraft.co.kr
        const val Email = "^[a-zA-Z0-9][a-zA-Z0-9_.-]+@[a-zA-Z.]+?\\.[a-zA-Z]{2,3}"
        const val MypageType = "^(EMAIL|PASSWORD|MOBILE)$"
        const val SearchKeyWordType = "^(AGENT|ADDRESS)$"
    }

    object Admin {
        // 닉네임: 한글, 영문, 공백 혼용 2자이상 20자 이내
        const val Name = "^[a-zA-Z가-힣\\s]{2,20}$"
        // 아이디: 영문 숫자 혼용 5자이상 20자 이내
        const val Id = "^[a-z]([a-z0-9]){4,19}$"
        // 비밀번호: 영문, 숫자, 특수 혼용 6~12자 이내
        // ㄴ 비밀번호 최소 하나의 문자 + 하나의 숫자 + 하나의 특수 문자 포함, 최소 6자리
        const val Password = "^(?=.*[A-Za-z])(?=.*\\d).{8,20}$"
        const val Email = "^[a-zA-Z0-9][a-zA-Z0-9_.-]+@[a-zA-Z.]+?\\.[a-zA-Z]{2,3}"
    }

    object Loan {
        const val RpayType = "^(PI|P|ED)$"
        const val LoanType = "^(M|R|C|V|B)$"
    }

    object Product {
        const val SearchKeyWordType = "^(APARTMENT|ADDRESS)$"
    }

    object Notification {
        const val SendType = "^(CALL|SMS|MESSAGE|PUSH)$"
    }

    object Common {
        const val Yn = "^(Y|N)$"
        // 실수
        const val Decimal = "^\\d*(\\.?\\d*)\$"
        // 정수
        const val Number = "^\\d*\$"
        // 도메인
        const val Url =  "^((http(s?))\\:\\/\\/)([0-9a-zA-Z\\-]+\\.)+[a-zA-Z]{2,6}(\\:[0-9]+)?(\\/\\S*)?$"
    }
}