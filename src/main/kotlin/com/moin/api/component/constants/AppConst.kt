package com.fin.best.bestfin.api.component.constants

import org.springframework.http.HttpStatus

/**
 * ConstantCode 정의 외에 기본적으로 필요한 상수값들을 정의
 */
object AppConst {
    const val KakaoGeoRegionTypeH: String = "H"

    object Common {
        const val YES = "Y"
        const val NO = "N"

        const val DEFAULT_ITEMS_PER_PAGE = 10    // 페이지네이션 기본 갯수
        const val DEFAULT_ORGANIZATION = "DAMBEE"

        const val SNS_JOIN_MOBILE = "3rd"

        const val VISITOR_MEMBER = 999999999
        
        const val ENV_PROD = "PROD"     // 운영환경 profile

        enum class TelecomCode(val code: String, val description: String) {
            NONE        ("00", ""),
            SKT         ("01", "SKT"),
            KT          ("02", "KT"),
            LGU         ("03", "LGU+"),
            SKT_A       ("04", "SKT 알뜰폰"),
            KT_A        ("05", "KT 알뜰폰"),
            LGU_A       ("06", "LGU+ 알뜰폰")
        }

        enum class BrowserType(val code: String, val description: String) {
            APP         ("APP",         "네이티브 앱"),
            PCWEB       ("PCWEB",       "데스크탑 웹"),
            MOBILEWEB   ("MOBILEWEB",   "모바일 웹")
        }

        enum class ProductSearchType(val code: String, val description: String) {
            ALL             ("ALL", "전체"),
            KB_PRODUCT      ("KBPRODUCT", "KB 시세"),
            JUSO_PRODUCT    ("JUSO", "주소 누리집")
        }
    }

    object ConstCode {
        const val AWS_CONFIGURATION = "1001"    // AWS Configuration

        const val GLOBAL_ENV     = "20000"      // Backend-api 전역 환경설정
        const val NEST_CONDITION = "20010"      // 보금자리론 서민실수요 조건
        const val LOAN_CALCULATE = "20011"      // 한도계산 상수데이터
        const val KCB_ALLCREDIT  = "20012"      // KCB 신용정보조회서비스
    }

    object User {
        const val DAMBEE_USAGE_MIN_AGE = 19         // 담비 서비스를 이용할 수 있는 최소 만 나이 (대출신청, 포인트 환금 등)

        const val UNIQUE_CODE_LENGTH = 6            // 사용자 고유키 길이

        const val USER_BIRTHDAY_LENGTH = 8          // YYYYMMDD

        @Deprecated("삭제 예정")
        const val NativeUserIdLength: Int = 18      // delete

        @Deprecated("삭제 예정")
        enum class UserAgent(val code: String, val secret: String, val role: String, val description: String) {
            @Deprecated("삭제 예정")
            AGENT(
                "best_lc-agent",
                "8D3F44CED8AFF7A0A84E4A29715E9F8C",
                "ROLE_AGENT",
                "부동산 유저 (legacy)"
            )
        }

        enum class UserType(val code: String, val secret: String, val role: String, val description: String) {
            DAMBEE_USER(
                "DAMBEE_USER",
                "8D3F44CED8AFF7A0A84E4A29715E9F8C",
                "ROLE_DAMBEE_USER",
                "담비 유저"
            ),
            BMS_USER(
                "BMS_USER",
                "",
                "ROLE_BMS_USER",
                "BMS 계정"
            ),
            CMS_USER(
                "CMS_USER",
                "",
                "ROLE_CMS_USER",
                "CMS 계정"
            ),
            COUNSELOR_USER(
                "COUNSELOR_USER",
                "8D3F44CED8AFF7A0A84E4A29715E9F8C",
                "ROLE_COUNSELOR_USER",
                "상담사 계정"
            ),
            REALESTATE_USER(
                "REALESTATE_USER",
                "8D3F44CED8AFF7A0A84E4A29715E9F8C",
                "ROLE_REALESTATE_USER",
                "부동산 중개업자 계정"
            ),
            COMPANY_EMPLOYEE(
                "COMPANY_EMPLOYEE",
                "",
                "",
                "업체 관계자 정보, 실계정 아님"
            )
        }

        enum class InflowRouteType(val code: String, val description: String) {
            GENERAL     ("GENERAL",     "일반 업체"),
            FINANCIAL   ("FINANCIAL",   "금융사"),
            AFFILIATE   ("AFFILIATE",   "제휴사"),
            COUNSELING  ("COUNSELING",  "상담업체")
        }

        enum class LoginAction(val code: String, val description: String) {
            LOGIN       ("LOGIN",       "로그인"),
            LOGOUT      ("LOGOUT",      "로그아웃"),
            EXPIRE      ("EXPIRE",      "토큰 만료"),
            DUPLICATE   ("DUPLICATE",   "중복 로그인 세션"),
            WITHDRAW    ("WITHDRAW",    "탈퇴")
        }

        enum class Gender(val code: String) {
            UNKNOWN     ("UNKNOWN"),
            MALE        ("MALE"),
            FEMALE      ("FEMALE")
        }

        enum class LFCode(val code: String, val description: String) {
            LOCAL       ("L", "내국인"),
            FOREIGNER   ("F", "외국인")
        }

        enum class Status(val code: String, val description: String) {
            NORMAL          ("NORMAL",          "정상"),
            BLOCK           ("BLOCK",           "정지"),
            CONFIRM_WAIT    ("CONFIRM_WAIT",    "승인 대기"),
            REFUSE          ("REFUSE",          "가입 거절"),
            WITHDRAW        ("WITHDRAW",        "탈퇴"),
            SLEEPER         ("SLEEPER",         "휴면계정")
        }

        enum class NotificationCode(val code: String, val description: String) {
            FEED                ("FEED", "피드 알림"),
            COMMUNITY           ("COMMUNITY", "커뮤니티 알림"),
            CREDIT              ("CREDIT", "신용정보 변경 알림"),
            MARKETING           ("MARKETING", "마케팅 정보 수신"),
            NIGHT_MARKETING     ("NIGHT_MARKETING", "야간 마케팅 정보 수신")
        }

        enum class MarketingTerms(val code: String, val description: String) {
            MARKETING                ("SA0004", "마케팅 약관 ID"),
            NIGHT_MARKETING          ("SA0005", "야간 마케팅 약관 ID")
        }

        enum class SendType(val code: String, val description: String) {
            CALL                ("CALL", "전화"),
            SMS                 ("SMS", "문자"),
            MESSAGE             ("MESSAGE", "인앱 메세지"),
            PUSH                ("PUSH", "앱 푸시")
        }
    }

    object ProviderLogin {
        const val STATE_PARAM = "state"
        const val USER_TYPE_PARAM = "user_type"
        const val INFLOW_ROUTE_PARAM = "inflow_route"
        const val INFLOW_COMPANY_PARAM = "inflow_company"
        const val REDIRECT_URI_PARAM = "redirect_uri"
        const val TERMS_AGREE_IDS_PARAM = "terms_agree_ids"

        const val ACCESS_TOKEN = "access_token"

        enum class SupportProvider(val code: String, val description: String) {
            KAKAO       ("KAKAO",       "카카오"),
            NAVER       ("NAVER",       "네이버"),
            GOOGLE      ("GOOGLE",      "구글"),
            APPLE       ("APPLE",       "애플"),
        }

        enum class SupportAffiliated(val code: String, val description: String) {
            PAYBOOC         ("PAYBOOC",     "페이북"),
            WELCOMESB       ("WELCOMESB",   "웰컴저축은행")
        }

        enum class SupportInflowRouteCompany(val code: String, val description: String) {
            LPOINT      ("LPOINT", "엘포인트"),
            ZEROPAY     ("ZEROPAY", "제로페이"),
            SSGPAY      ("SSGPAY", "SSG페이")
        }
    }

    object OkCert {
        const val SITE_URL = "www.dambee.com"
        const val SITE_NAME = "베스트핀"
        const val CHANNEL_CODE = "0000"
        const val COMPANY_CODE = "V50520000000"

        const val REQUEST_TEST = "TEST"     // 동작 안함
        const val REQUEST_PROD = "PROD"

        const val FUNC_SMS_REQUEST = "IDS_HS_EMBED_SMS_REQ"         // sms 인증번호 요청
        const val FUNC_SMS_VERIFICATION = "IDS_HS_EMBED_SMS_CIDI"   // sms 인증번호 검증

        enum class GenderCode(val code: String) {
            UNKNOWN("U"),
            MALE("M"),
            FEMALE("F")
        }

        enum class RequestReasonCode(val code: String, val description: String) {
            JOIN        ("00", "회원가입"),
            ADULT_CERT  ("01", "성인인증"),
            MODIFY_INFO ("02", "회원정보수정"),
            FIND_PASS   ("03", "비밀번호찾기"),
            BUY         ("04", "상품구매"),
            ETC         ("99", "기타")
        }

        enum class ResponseCode(val code: String, val description: String) {
            OK                  ("B000", "정상처리"),
            CANCEL              ("B123", "본인인증 취소"),
            ALREADY_PROCESS     ("B128", "이미 완료된 본인인증"),
            INPUT_TIME_OVER     ("B129", "인증번호 입력시간 초과"),
            INVALID_OTP         ("B131", "인증번호 불일치"),
            RESEND_TIME_OVER    ("B136", "인증번호 재전송 요청시간 초과")
        }
    }

    object Allcredit {
        const val NOT_REGISTERED_USER_NO = "0"      // Allcredit 미가입 계정

        enum class ResponseCode(val code: String, val description: String) {
            OK                  ("00000", "성공"),
            URL_OK              ("E000", "성공"),
            NOT_FOUND_USER_NO   ("E2411", "사용자 번호 조회 실패"),
            ALREADY_JOIN        ("E2514", "이미 가입된 계정"),
            WITHDRAW_NOT_FOUND  ("E2711", "탈퇴 회원정보 없음"),
            PUBLIC_TIME_ERROR   ("E4001", "거래가능시간이 아닙니다."),
            PUBLIC_AGREE_ERROR  ("E4002", "동의처리에 실패하였습니다."),
            MOIS_ERROR          ("E4003", "행안부 통신 장애입니다"),
            PUBLIC_EXIST        ("E4004", "유효정보 기 존재"),
            PUBLIC_DUPLICATE    ("E4005", "보유기관 정보 미제공 (당일 중복 호출 제한)"),
            UNKOWN_ERROR        ("E9999", "기타 오류")
        }

        enum class Status(val code: String, val description: String) {
            NONE            ("NONE", "가입 이력 없음"),
            NORMAL          ("NORMAL", "가입"),
            WITHDRAW        ("WITHDRAW", "탈퇴"),
            OTHER           ("OTHER", "다른 경로 가입"),
            ENV             ("ENV", "다른 환경 가입")
        }

        enum class IncreaseStatus(val code: String, val description: String) {
            REQUEST         ("REQUEST", "신용올리기 시도 중"),
            COMPLETE        ("COMPLETE", "신용올리기 완료")
        }

        const val DELETE_MYDATA_TARGET_CODE = "all"     // 등록유형
        const val DELETE_MYDATA_REASON_CODE = "3"       // 등록사유구분코드
    }

    object Counselor {
        enum class InflowType(val code: String, val description: String) {
            NORMAL          ("NORMAL",      "일반가입"),
            CORPORATION     ("CORPORATION", "법인가입")
        }
    }

    object Counsel {
        enum class LoanType(val code: String, val description: String) {
            MORTGAGE    ("M", "주택담보대출"),
            RENT        ("R", "전월세대출"),
            CREDIT      ("C", "신용대출")
        }

        enum class FinSector(val code: String, val description: String) {
            BANK        ("BANK", "은행"),
            INSURANCE   ("INSURANCE", "보험회사"),
            MUTUAL      ("MUTUAL", "상호금융"),
            CARD        ("CARD", "신용카드사"),
            CAPITAL     ("CAPITAL", "캐피탈"),
            SAVINGS     ("SAVINGS", "저축은행"),
            P2P         ("P2P", "P2P")
        }

        enum class Status(val code: String, val description: String) {
            COMPARE     ("COMPARE", "비교"),
            REQUEST     ("REQUEST", "대출신청"),
            FIRSTCALL   ("FIRSTCALL", "상담사 1차 매칭 중"),
            SECONDCALL  ("SECONDCALL", "상담사 2차 매칭 중"),
            FAILEDCALL  ("FAILEDCALL", "상담사 매칭 실패"),
            APICALL     ("APICALL", "API 신청 진행 중"),
            RPACALL     ("RPACALL", "RPA 신청 진행 중"),
            PROCEEDING  ("PROCEEDING", "상담 진행 중"),
            APPLY       ("APPLY", "대출 신청 중"),
            FILTERING   ("FILTERING", "조건 필터링"),
            CANCEL      ("CANCEL", "상담 취소"),
            REJECT      ("REJECT", "거절"),
            COMPLETE    ("COMPLETE", "대출신청 완료"),
            EXPIRE      ("EXPIRE", "만료")
        }

        enum class FilterCondition(val code: String, val description: String) {
            EQ      ("EQ", "="),
            NE      ("NE", "!="),
            GT      ("GT", ">="),
            G       ("G", ">"),
            LT      ("LT", "<="),
            L       ("L", "<"),
            IN      ("IN", "IN"),
            NOT     ("NOT", "NOT"),
            RANGE   ("RANGE", "RANGE"),
            EXC     ("EXC", "EXCLUSIVE")
        }

        enum class CalculateType(val code: String, val description: String) {
            DAMBEE  ("DAMBEE", "담비 계산"),
            BANK    ("BANK",   "은행 계산"),
            TABLE   ("TABLE",  "조견표 계산")
        }

        enum class RouteType(val code: String, val description: String) {
            OUTLINK     ("OUTLINK",     "APP, WEB 등의 outlink redirection"),
            MAIL        ("MAIL",        "메일 전송"),
            API         ("API",         "API 신청"),
            APILINK     ("APILINK",     "API 신청 + outlink"),
            COUNSELOR   ("COUNSELOR",   "상담사 매칭"),
            AUTO        ("AUTO",        "RPA 사용")
        }

        enum class HouseTypeCode(val code: String, val description: String) {
            A   ("A", "아파트"),
            O   ("O", "오피스텔"),
            E   ("E", "기타/직접입력")
        }

        enum class RegulationType(val code: String, val description: String) {
            S   ("S", "투기지역"),
            R   ("R", "투기과열지역"),
            A   ("A", "조정지역"),
            N   ("N", "해당없음")
        }

        enum class UsageType(val code: String, val description: String) {
            BUY         ("BUY", "주택구입"),
            LIFE        ("LIFE", "생활자금 (전월세 포함)"),
            REFINANCE   ("REFINANCE", "대환대출"),
            BUSINESS    ("BUSINESS", "사업자대출"),
            DEPOSIT     ("DEPOSIT", "세입자보증금반환"),
            RENT        ("RENT", "전월세 입주자금"),
            CREDIT      ("CREDIT", "신용대출")
        }

        enum class OwnHouseType(val code: String, val description: String) {
            NONE    ("NONE",    "보유없음"),
            SINGLE  ("SINGLE",  "1주택 보유"),
            MULTI   ("MULTI",   "2주택 이상 보유"),
        }

        enum class JobType(val code: String, val description: String) {
            SALARYEARNER    ("SALARYEARNER" ,"급여소득자"),
            BUSINESS        ("BUSINESS", "개인사업자"),
            ETC             ("ETC", "기타")
        }
    }

    object Community {
        const val NICKNAME_CONFIRM_EXPIRE   = 600       // 닉네임 중복확인 유효시간 (초)

        const val LIMIT_KEYWORD_LIST        = 5         // 키워드 조회 제한 수
        const val LIMIT_BESTPICK_LIST       = 2         // 베스트픽 조회 제한 수

        enum class UserStatus(val code: String, val description: String) {
            ACTIVE      ("ACTIVE", "활동상태"),
            INACTIVE    ("INACTIVE", "정지상태")
        }

        enum class PostType(val code: String, val description: String) {
            USER        ("USER", "유저작성"),
            ADMIN       ("ADMIN", "관리자작성")
        }

        enum class OrderType(val code: String, val description: String) {
            NEWEST      ("NEWEST", "최신순"),
            COMMENT     ("COMMENT", "댓글순"),
            REPLY       ("REPLY", "답글순"),
            LIKE        ("LIKE", "좋아요순"),
            READ        ("READ", "조회수순")
        }

        enum class WriteType(val code: String, val description: String) {
            POST        ("POST", "게시글"),
            COMMENT     ("COMMENT", "댓글"),
            REPLY       ("REPLY", "답글")
        }

        enum class SearchOption(val code: String, val description: String) {
            ALL         ("ALL", "전체검색"),
            KEYWORD     ("KEYWORD", "키워드검색"),
            TITLE       ("TITLE", "제목검색"),
            CONTENTS    ("CONTENTS", "내용검색"),
            USER        ("USER", "글쓴이검색")
        }

        enum class PostDisableReasonCode(val code: String, val description: String) {
            USER_DELETED        ("USER_DELETED", "유저 삭제"),
            ADMIN_DELETED       ("ADMIN_DELETED", "관리자 삭제")
        }

        enum class PostReportReasonCode(val code: String, val description: String) {
            NOTICE              ("NOTICE", "공지사항 위반"),
            UNPLEASANT          ("UNPLEASANT", "불쾌한 표현 사용"),
            DUPLICATE           ("DUPLICATE", "중복 게시글 작성"),
            ADVERTISING         ("ADVERTISING", "홍보성 글 작성"),
            HARMFUL             ("HARMFUL", "유해성 글 작성"),
            ILLEGALITY          ("ILLEGALITY", "불법정보 게시"),
            PRIVACY             ("PRIVACY", "개인정보 유출"),
            SUBJECT             ("SUBJECT", "주제와 무관"),
            ETC                 ("ETC", "기타 - 직접 입력")
        }

        enum class ContentsDisableDetailCode(val code: String, val description: String) {
            POST_DELETED        ("POST_DELETED", "존재하지 않는 게시글입니다."),
            COMMENT_DELETED     ("COMMENT_DELETED", "존재하지 않는 댓글입니다.")
        }

        enum class ReportStatus(val code: String, val description: String) {
            REPORT              ("REPORT", "신고"),
            PROCEEDING          ("PROCEEDING", "처리중"),
            REJECT              ("REJECT", "반려"),
            CANCEL              ("CANCEL", "처리 취소"),
            COMPLETE            ("COMPLETE", "처리 완료")
        }

        enum class ActionCode(val code: String, val description: String) {
            SET_NICKNAME        ("SET_NICKNAME", "닉네임 설정"),
            POST_NEW            ("POST_NEW", "게시글 작성"),
            POST_UPDATE         ("POST_UPDATE", "게시글 수정"),
            POST_DELETE         ("POST_DELETE", "게시글 삭제"),
            POST_LIKE           ("POST_LIKE", "게시글 좋아요 클릭"),
            POST_UNLIKE         ("POST_UNLIKE", "게시글 좋아요 해제"),
            POST_READ           ("POST_READ", "게시글 상세 조회"),
            POST_BOOKMARK       ("POST_BOOKMARK", "게시글 즐겨찾기"),
            COMMENT_NEW         ("COMMENT_NEW", "댓글 작성"),
            COMMENT_UPDATE      ("COMMENT_UPDATE", "댓글 수정"),
            COMMENT_DELETE      ("COMMENT_DELETE", "댓글 삭제"),
            COMMENT_LIKE        ("COMMENT_LIKE", "댓글 좋아요 클릭"),
            COMMENT_UNLIKE      ("COMMENT_UNLIKE", "댓글 좋아요 해제"),
            REPLY_NEW           ("REPLY_NEW", "답글 작성"),
            REPLY_UPDATE        ("REPLY_UPDATE", "답글 수정"),
            REPLY_DELETE        ("REPLY_DELETE", "답글 삭제"),
            REPLY_LIKE          ("REPLY_LIKE", "답글 좋아요 클릭"),
            REPLY_UNLIKE        ("REPLY_UNLIKE", "답글 좋아요 해제"),
            REPORT_POST         ("REPORT_POST", "게시글 신고"),
            REPORT_COMMENT      ("REPORT_COMMENT", "댓글 신고"),
            REPORT_REPLY        ("REPORT_REPLY", "답글 신고"),
            REPORT_USER         ("REPORT_USER", "유저 신고"),
            USER_FOLLOW         ("USER_FOLLOW", "유저 팔로우"),
            USER_UNFOLLOW       ("USER_UNFOLLOW", "유저 언팔로우")
        }

        enum class PushMessage (val code: String, val message: String) {
            COMMENT         ("COMMENT", "게시글에 댓글이 달렸습니다."),
            LIKE            ("LIKE", "게시글을 좋아하는 사람이 있습니다.")
        }
    }

    object Advert {
        const val DEFAULT_ADVERT_COUNT = 5      // 기본 광고 수

        enum class AreaType(val code: String, val description: String) {
            APP_MAIN_BOTTOM         ("APP_MAIN_BOTTOM", "앱 메인 바텀"),
            APP_MAIN_ROLLING        ("APP_MAIN_ROLLING", "앱 메인 롤링 배너"),
            APP_PRICE_LAYER         ("APP_PRICE_LAYER", "앱 시세 레이어 팝업"),
            APP_COMMUNITY_LAYER     ("APP_COMMUNITY_LAYER", "앱 커뮤니티 레이어 팝업"),
            APP_MENU_ROLLING        ("APP_MENU_ROLLING", "앱 메뉴 롤링 배너"),
            APP_CREDIT_COMPARE      ("APP_CREDIT_COMPARE", "앱 신용대출 비교 결과"),
            APP_COUNSEL_REQUEST     ("APP_COUNSEL_REQUEST", "앱 대출 신청완료"),
            WEB_MAIN                ("WEB_MAIN", "홈페이지 메인 (PC/모바일)")
        }
    }

    enum class TemporaryKeyReason(val code: String, val description: String) {
        APP_PIN_RESET       ("APP_PIN_RESET", "APP PIN 재발급 요청")
    }

    object FinancialCompany {
        // 2023-01-02 기준 product_base 상품의 금융사에 대해서만 정의
        const val SC = "0010002" //SC제일은행
        const val DGB = "0010016" //대구은행
        const val BNK_BUSAN = "0010017" //부산은행
        const val BNK_GYOUNGNAM = "0010024" // 경남은행
        const val JB = "0010267" //JB우리캐피탈㈜
        const val OSB = "0010346" //OSB저축은행
        const val SBI = "0010370" //SBI저축은행
        const val GORYO = "0010390" //고려저축은행
        const val HANHWA = "0010593" //한화생명
        const val SAMSUNG_FIRE = "0010633" //삼성화재
        const val OK = "0013351" // OK저축은행
        const val PEPPER = "0010471" // 페퍼
        const val NHCAPITAL = "0011797" // NH 농협캐피탈
        const val WELCOME = "0013350" // 웰컴 저축은행
        const val PEOPLEFUND = "0900001" // 피플펀드 (2023-01-13 반영)
        const val TOGETHERFUNDING = "0900002"   // 투게더펀딩
        const val LEADINGPLUS = "0900003"   // 리딩플러스
        const val DAILYFUNDING = "0900004"  // 데일리펀딩
        const val OCEANFUNDING = "0900005"  // 오션펀딩
        const val ANYANG = "0010463"   // 안양저축은행
        const val DOUBLESB = "0010528"  // 더블저축은행
        const val HONESTFUND = "0900006"  // 어니스트펀드
        const val EIGHT_PERCENT = "0900007"     // 8퍼센트
    }


    enum class RpaStatus {
        START, SEND, RESEND, COMPLETE, CANCEL, FAILED
    }


    @Deprecated("삭제 예정")
    object Promotion {
        enum class ProdPromotionCode(val code: String, val beginDate: String, val endDate: String) {
            KOREA_FINTECH_WEEK_2022("KFW2022", "20220928", "20220930"),     // 코리아 핀테크 위크 2022
            SEOUL_MONEY_SHOW_2023("M2023", "20230511", "20230513"),         // 서울 머니쇼 2023
            NEXTRISE_2023("N2023", "20230601", "20230602")                  // 넥스트라이즈 2023
        }

        // 개발용
        enum class DevPromotionCode(val code: String, val beginDate: String, val endDate: String) {
            KOREA_FINTECH_WEEK_2022("KFW2022", "20220928", "20220930"),     // 코리아 핀테크 위크 2022
            SEOUL_MONEY_SHOW_2023("M2023", "20230508", "20230513"),         // 서울 머니쇼 2023
            NEXTRISE_2023("N2023", "20230523", "20230902")                  // 넥스트라이즈 2023
        }
    }

    object Loan {
        const val CounselNoteProceeding: String = "\"%s\"님과 \"%s\" 상담사님의 \"%s\", \"%s％\" 상품에 대한 대출 상담이 시작되었습니다."
        const val CounselNoteApply: String = "\"%s\" 상담사님이 \"%s\", \"%s％\" 상품의 대출 신청을 진행했습니다."
        const val CounselNoteComplete: String = "\"%s\"님의 \"%s\", \"%s％\" 상품의 대출이 완료되었습니다."
        const val CounselNoteCancel: String = "\"%s\"님이 상담을 취소하셨습니다."
        const val CounselNoteCancelByCounselor: String = "\"%s\" 상담사님이 상담을 취소하셨습니다."
    }

    object AppVersion {
        enum class OsType(val code: String, val description: String) {
            ALL         ("ALL", "전체"),
            AOS         ("AOS", "안드로이드"),
            IOS         ("IOS", "애플")
        }

        enum class AppType(val code: String, val description: String) {
            ALL         ("ALL", "전체"),
            DAMBEE      ("DAMBEE_APP", "담비 사용자"),
            COUNSELOR   ("COUNSELOR_APP", "상담사")
        }
    }

    object Message {

        const val BANK_CONTACT = "유선상%s으로"

        const val MessageFormat: String = "[담비] 인증번호는 [%s] 입니다.(타인 노출 금지)"
        const val MessageHashFormat: String = "<#>[담비] 인증번호는 [%s] 입니다. %s"
        const val UserRequestToBank: String = "[담비]담보대출비교 단번에! \n" +
                                                "\"%s\"님, 담비에서 선택하신 \"%s\" 으로 \"%s\" 에서  대출상담 신청이 접수되었습니다.\n" +
                                                "\"%s\"  담당자가 영업시간 내 %s 직접 연락드릴 예정입니다.\n" +
                                                "이용해 주셔서 감사합니다.\n\n" +
                                                "※담비 고객센터 : 1533-2333"
        const val CounselRequestToUser: String = "\"%s\"님, \"%s\" 님으로의 상담이 접수되었습니다. 24시간내(공휴일 제외)에 상담사님께서 고객님에게 상담을 드릴 예정입니다."
        const val CounselRequestToCounselor: String = "\"%s\"님이 대출 상담을 신청하였습니다. 친절한 상담 및 안내를 부탁드립니다."
        const val CounselRequestFirstDelayToCounselor: String = "\"%s\"님의 상담 대기가 2시간을 초과하였습니다. 확인을 부탁드립니다."
        const val CounselRequestFirstDelayToLeader: String = "팀원\"%s\"님께 접수된 \"%s\"님의 상담 대기가 2시간을 초과하였습니다. 확인후 해당 상담사님이 상담을 진행 못하실 경우, 관리자에게 보고 부탁해주세요."
        const val CounselRequestSecondaryDelayToCounselor: String = "\"%s\"님의 상담 대기가 4시간을 초과하였습니다. 확인을 부탁드립니다."
        const val CounselRequestSecondaryDelayToLeader: String = "팀원\"%s\"님께 접수된 \"%s\"님의 상담 대기가 4시간을 초과하였습니다.  확인후 해당 상담사님이 상담을 진행 못하실 경우, 관리자에게 보고 부탁해주세요."
        const val CounselRequestSecondaryDelayToAdmin: String = "\"%s\" 상담사님의 \"%s\"님에 대한 대출 상담 대기가 4시간을 초과하였습니다. 어드민 관리자 사이트에서 확인 및 조치 부탁 드립니다."
        const val CounselCounselorChangeInfo: String = "\"%s\"님, \"%s\"님께서 \"%s\"님에게 신청한 대출 상담건이 해당 상담사님의 개인적 사정으로 인해 \"%s\" 상담자님께 인계되었습니다. 고객님의 양해를 부탁드립니다. 혹시 관련하여 문의사항이 있으실 경우, 고객 센터 02) 761-3330로 연락 주세요. "
        const val CounselApplyToUser: String = "\"%s\"님, \"%s\"대출 상품의 대출 신청이 해당 금융사에 접수되었습니다."
        const val CounselCompleteToUser: String = "\"%s\"님, 고객님의 \"%s\" 대출이 실행 완료되었습니다. 베스트핀을 이용해주셔서 감사합니다."

        object Counsel {
            const val CounselCall: String = "상담사님의 활동지역에 새로운 상담건이 등록되었습니다."
            const val CounselProceeding: String = "[\"%s\"][전화번호 : \"%s\"] 상담사님이 고객님의 상담[\"%s\", \"%s\"]을 접수하셨습니다. 곧 상담사님이 개별 연락드릴 예정이에요. 친절하고 정확한 상담을 약속 드리겠습니다"
            const val CounselApply: String = "[\"%s\"] 상담사님이 고객님의 신청 [\"%s\", \"%s\"] 정보를 해당 금융사에 제출 완료했습니다. 자세한 대출 일정은 상담사님께 문의해주세요."
            const val CounselComplete: String = "축하 드립니다! 고객님의 대출[\"%s\", \"%s\"] 이 성공적으로 완료되었습니다.  담비와 함께 해주셔서 정말 감사드려요!"
            const val CounselCancel: String = "고객님의 대출[\"%s\", \"%s\"] 이 취소 처리[\"%s\", \"%s\"] 되었습니다."

            const val NotFoundUser: String = "대상을 찾을 수 없습니다."
            const val CounselLimitAge: String = "만 19세 이상부터 대출신청이 가능합니다."
        }

        object Reward {
            const val addPoint: String = "포인트 적립이 되었어요."
            const val pointBatchProcess: String = "상품권 교환 신청 가능 시간은 00:30 부터  23:59 까지 입니다."
            const val conversionPoint: String = "상품권 교환"
            const val usePoint: String = "포인트 사용"
        }
    }

    object Request {
        const val RequestHeaderAccessTokenKey = "access-token"
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

    object Cache {
        const val CachedPrefixAuthHashKey = "acc"
        const val SimulationHashKey = "loan:sim"
        const val KbProductSearchKeywordRankHashKey = "prod:rank"
        const val KbProductUploadHashKey = "prod:kb:upload"
        const val UserPinCodeHashKey: String = "usr:pin"
        const val CounselorPinCodeHashKey: String = "counselor:pin"
        const val PhoneCertHashKey: String = "phone:cert"
        const val PaymentDataPrebuild: String = "payment"
        const val DailyAccessUserStatsHashKey: String = "dau"
        const val AccrueDailyAccessUserStatsHashKey: String = "accrueDau"
        const val MonthlyAccessUserStatsHashKey: String = "mau"
        const val FcmPushQueueKey: String = "fcm:push:queue"
        const val FinanceHashKey = "common:finance"
        const val FinancePartnerHashKey = "common:finance-partner"
        const val BasicRateHashKey = "common:rate"
        const val CallCounselHashKey = "loan:call"
        const val EmailPushQueueKey: String = "email:push:queue"
    }

    object RedisCache {
        const val ProviderLoginKey = "provider:login"
        const val ProviderLoginTTL = 60 * 10L

        const val UserLoginKey = "user"
        const val UserLoginTTL = 60 * 60 * 24 * 30L

        const val PinSetupKey = "pin:setup"
        const val PinSetupTTL = 60 * 3L

        const val PinChangeKey = "pin:change"
        const val PinChangeTTL = 60 * 3L

        const val TemporaryKey = "temporary:key"
        const val TemporaryKeyTTL = 60 * 5L

        const val KcbAllcreditAccessToken = "kcballcredit"
    }

    object Response {
        const val DefaultSuccessCode = 200
        const val DefaultEmptyCode = 0
        const val DefaultSuccessMessage = "success"
        const val DefaultEmptyMessage = ""
        const val DefaultFailedMessage = "failed"
        const val DefaultExceptionMessage = "exception"
    }

    object ResponseCode {
        const val Success = 200
        const val DataNotFound = 400                      //조건에 맞는 상품 없음(정상조건)
        const val Unauthorized = 401                      //조건에 맞는 상품 없음(정상조건)
        const val AccessTokenTimeout = 600               //만료된 토큰
        const val RefreshTokenTimeout = 601              //만료된 토큰
        const val ApplicationParameterError = 10000     //Application 파라미터 오류
        const val DatabaseQueryError = 10002            //내부 오류
        const val RequestNotFoundError = 10003          //조건에 맞는 상품 없음(비정상조건)
        const val InputValidationError = 10004          //입력값 오류
        const val UpdateQueryError = 10005              //정보 갱신 실패(내부 오류)
        const val ProductErrorLoanLimitAmountNotAvailable = 20001  //대출불가 조건, 담보물 및 소득 확인 요망
        const val ProductErrorInvalidInterestRateInput = 20002     //대출 희망 이자 조건 오류
        const val ProductErrorPrimeRate = 20003         //우대금리 조건 오류

        //정상조건(입력조건확인)
        const val PleaseCheckLoanUsage = 30001         //대출용도 확인 요망
        const val InsufficientLoanLimit = 30002         //대출한도 부족, 희망 금액 수정 요망
        const val PleaseCheckMortagae = 30022         //담보물 확인 요망

        const val CounselLimitAge = 30031           // 대출가능 나이 제한

        //정상조건(status 메세지 팝업)
        const val InvalidServiceTime = 40001                 //서비스 제공 시간외 접근
        const val InvalidServiceCondition = 40002                 //서비스 접근 체크 제한
    }

    object Log {
        const val Domain: String = "com.fin.best.bestfin.api"
        const val BasePackage: String = "com.fin.best.bestfin"
        const val ApplicationName: String = "dambee_service"
        const val Request: String = "REQ"
        const val Response: String = "RES"
        const val ContentType: String = "Content-Type"
        const val ContentLength: String = "Content-Length"
        const val UserAgent: String = "User-Agent"
        const val Method: String = "method"
        const val Uri: String = "uri"
        const val Status: String = "status"
        const val ErrorCode: String = "errorCode"
        const val ErrorMessage: String = "errorMessages"
        const val Elapsed: String = "elapsed"
    }

    object Delimiters {
        const val PadBar: String = " | "
        const val IsVal: String = "="
        const val Dash: String = "-"
        const val Unknown: String = "UNKNOWN"
        const val Comma: String = ","
        const val Dot: String = "."
        const val LineBreakAt: String = "\n \t at "
        const val Question: String = "?"
        const val LineBreak: String = "\n"
        const val Colon: String = ":"
    }

    object File {
        const val DefaultSystemFileMime: String = "application/octet-stream"
        const val XlsFileMimeType: String = "application/vnd.ms-excel"
    }

    object Address {
        const val SidoSeoul: String = "서울특별시"
        const val SidoGyunggi: String = "경기도"
        const val SidoGwangju: String = "광주광역시"
        const val SidoDaegu: String = "대구광역시"
        const val SidoDaejeon: String = "대전광역시"
        const val SidoBusan: String = "부산광역시"
        const val SidoIncheon: String = "인천광역시"
        const val SidoUlsan: String = "울산광역시"
        const val SidoGangwon: String = "강원도"
        const val SidoGyeongnam: String = "경상남도"
        const val SidoGyeongbuk: String = "경상북도"
        const val SidoJeonnam: String = "전라남도"
        const val SidoJeonbuk: String = "전라북도"
        const val SidoChungnam: String = "충청남도"
        const val SidoChungbuk: String = "충청북도"
        const val SidoJeju: String = "제주특별자치도"
        const val SidoSejong: String = "세종특별자치시"
    }

    object Payment {
        const val PaymentSuccessCode: String = "0000"
        const val PaymentMobileSuccessCode: String = "00"
        object PaymentTypeKeys {
            const val CARD = "CARD"
            const val MOBILE = "MOBILE"
            const val BANK = "BANK"
            const val Card = "Card"
            const val HPP = "HPP"
            const val DirectBank = "DirectBank"
        }
    }

    object RewardPolicyContentsCode {
        enum class ProvideCode(val code: String, val optionName: String) {
            ALL("ALL", "사용자 전체"),
            MARKETING_AGREEMENT("PROVIDE_MARKETING", "사용자 마케팅 동의"),
            AGENT("AGENT", "부동산 파트너"),
            SPECIFIC_TARGET("SPECIFIC_TARGET", "대상 업로드");
        }

        enum class MissionCode(val code: String, val missionName: String) {
            APP_JOIN("APP_JOIN", "담비 앱 회원가입"),
            APP_RECOMMEND_JOIN("APP_RECOMMEND_JOIN", "담비 앱 피추천인 가입"),
            APP_PRODUCT_COMPARE("APP_PRODUCT_COMPARE", "담비 앱 대출 비교"),
            APP_COUNSEL_REQUEST("APP_COUNSEL_REQUEST", "담비 앱 대출 신청"),
            APP_COUNSEL_COMPLETE("APP_COUNSEL_COMPLETE", "담비 앱 대출 완료"),
            APP_FEED_LIKE("APP_FEED_LIKE", "담비 앱 피드 LIKE"),
            PAYBOOC_COUNSEL_REQUEST("PAYBOOC_COUNSEL_REQUEST", "BC페이북 대출 신청"),
            PAYBOOC_COUNSEL_COMPLETE("PAYBOOC_COUNSEL_COMPLETE", "BC페이북 대출 완료"),
            AGENT_RECOMMEND_JOIN("AGENT_RECOMMEND_JOIN", "부동산 추천 가입");
        }

        enum class PaymentMethodCode(val code: String, val method: String) {
            IMMEDIATELY("IMMEDIATELY", "즉시"),
            DELAY("DELAY", "지연")
        }

        enum class TargetPlatformCode(val code: String, val platformName: String) {
            APP("APP", "담비 APP"),
            WEB("WEB", "담비 WEB")
        }

        enum class PaymentTypeCode(val code: String, val type: String) {
            EARN("EARN", "지급"),
            USE("USE", "사용"),
            EXPIRED("EXPIRE", "소멸"),
            ADMIN_EARN("ADMIN_EARN", "관리자 지급"),
            ADMIN_USE("ADMIN_USE", "관리자 사용"),
            WITHDRAW("WITHDRAW", "회원 탈퇴"),
        }

        enum class ExchangeStatusCode(val code: String, val type: String) {
            APPLY("APPLY", "신청"),
            CONFIRM("CONFIRM", "확정"),
            COMPLETE("COMPLETE", "완료"),
            CANCEL("CANCEL", "취소")
        }

        enum class MaxPeriodType(val code: String, val type: String) {
            DAY("DAY", "일간"),
            WEEK("WEEK", "주간"),
            MONTH("MONTH", "월간"),
            YEAR("YEAR", "연간"),
        }
    }

    object Terms {
        const val TERMS_ID_PREFIX_DIGITS = 2
        const val TERMS_ID_SUFFIX_DIGITS = 4
        const val TERMS_DEFAULT_VERSION = "1.0.0"

        const val SERVICE_TERMS_TYPE = "S"
        const val AGREE_TERMS_TYPE = "A"
        const val COMPANY_TERMS_TYPE = "C"

        const val CONFIRM_STATUS_WAIT = "WAIT"
        const val CONFIRM_STATUS_CONFIRM = "CONFIRM"
        const val CONFIRM_STATUS_REJECT = "REJECT"

        const val TARGET_USER = "USER"
        const val TARGET_AGENT = "AGENT"
        const val TARGET_COUNSELOR = "COUNSELOR"

        const val CONTENT_TYPE_HTML = "HTML"
        const val CONTENT_TYPE_URL = "URL"

        enum class TermsPrefix(val desc: String) {
            SA("회원가입 서비스 약관"),
            AL("대출신청 이용동의"),
            KA("KCB 본인인증 약관"),
            KB("KCB 신용송부서비스 약관"),
            KC("KCB 신용점수 올리기 약관"),
            SL("삼성생명 대출신청 약관"),
            SF("삼성화재 대출신청 약관")
        }
    }

    enum class ResCode(val httpStatus: HttpStatus, val detail: String) {

        /****************************** 공통 ******************************/
        OK                              (HttpStatus.OK, "성공"),

        REQUIRED_DATA_PARAMETER         (HttpStatus.BAD_REQUEST, "필수 파라미터 누락"),
        INVALID_PARAMETER               (HttpStatus.BAD_REQUEST, "유효하지 않은 파라미터"),
        INVALID_DATA_TYPE               (HttpStatus.BAD_REQUEST, "유효하지 않은 데이터 타입"),
        INVALID_YN_TYPE                 (HttpStatus.BAD_REQUEST, "유효한 YN 아님"),
        INVALID_CONSTANT_TYPE           (HttpStatus.BAD_REQUEST, "유효하지 않은 상수 데이터"),
        INVALID_REGEX_TYPE              (HttpStatus.BAD_REQUEST, "형식에 맞지 않는 데이터"),
        NOT_FOUND_DATA                  (HttpStatus.NOT_FOUND, "조회 데이터 없음"),
        INTERVAL_SERVER_ERROR           (HttpStatus.INTERNAL_SERVER_ERROR, "서버 장애"),
        INVALID_ACCESS_TOKEN            (HttpStatus.UNAUTHORIZED, "잘못된 access token"),
        NO_CERT_ACCOUNT                 (HttpStatus.BAD_REQUEST, "본인인증되지 않은 계정입니다."),
        LIMIT_MINIMUM_AGE               (HttpStatus.UNAUTHORIZED, "19세 미만 미성년자는 서비스 사용이 불가합니다."),
        NOT_FOUND_LOGIN_INFO            (HttpStatus.UNAUTHORIZED, "로그인 정보를 찾을 수 없습니다."),
        NOT_FOUND_COMPANY_INFO          (HttpStatus.NOT_FOUND, "업체 정보를 찾을 수 없습니다."),
        SEND_EMAIL_ERROR                (HttpStatus.INTERNAL_SERVER_ERROR, "이메일 전송에 실패하였습니다."),
        INVALID_REASON_TYPE             (HttpStatus.BAD_REQUEST, "잘못된 사유입니다."),
        NOT_FOUND_SECURE_KEY            (HttpStatus.BAD_REQUEST, "키를 찾을 수 없습니다."),
        EXPIRED_SECURE_KEY              (HttpStatus.BAD_REQUEST, "만료된 키입니다."),
        INACTIVATED_ACCESS_TOKEN        (HttpStatus.UNAUTHORIZED, "이미 다른 기기 및 경로에서 로그인 되어있습니다.\n다른 기기 및 경로에서 로그아웃 후 로그인 해주세요."),
        /****************************** 공통 ******************************/

        /****************************** 인증 ******************************/
        AUTH_NOT_FOUND_PROVIDER_CACHE   (HttpStatus.NOT_FOUND, "인증 정보를 찾을 수 없음"),
        AUTH_INVALID_USER_TYPE          (HttpStatus.BAD_REQUEST, "잘못된 유저 타입"),
        AUTH_NO_CERT_ACCOUNT            (HttpStatus.UNAUTHORIZED, "본인인증되지 않은 계정"),
        AUTH_INVALID_PIN_CODE           (HttpStatus.BAD_REQUEST, "잘못된 PIN 코드"),
        AUTH_NO_INPUT_PIN_CODE          (HttpStatus.BAD_REQUEST, "PIN 설정 이력이 없음"),
        AUTH_NO_MATCH_PIN_CODE          (HttpStatus.BAD_REQUEST, "PIN 매칭 오류"),
        AUTH_STORE_TOKEN_ERROR          (HttpStatus.BAD_REQUEST, "토큰 공백 저장 오류"),
        AUTH_CREATE_TOKEN_ERROR         (HttpStatus.INTERNAL_SERVER_ERROR, "토큰 생성 오류"),
        AUTH_CREATE_USER_ERROR          (HttpStatus.INTERNAL_SERVER_ERROR, "계정 생성 오류"),
        AUTH_LOGIN_HISTORY_ERROR        (HttpStatus.INTERNAL_SERVER_ERROR, "로그인 히스토리 생성 오류"),
        AUTH_MISMATCH_PIN_CODE          (HttpStatus.BAD_REQUEST, "PIN 입력 오류"),
        AUTH_NOT_FOUND_PIN_CHANGE_CACHE (HttpStatus.NOT_FOUND, "PIN 변경정보 없음"),
        AUTH_MISMATCH_PIN_CHANGE_INFO   (HttpStatus.BAD_REQUEST, "PIN 변경정보 일치하지 않음"),
        AUTH_ALREADY_RENEWAL            (HttpStatus.BAD_REQUEST, "이미 리뉴얼 처리된 계정"),
        AUTH_NO_RENEWAL_ACCOUNT         (HttpStatus.BAD_REQUEST, "리뉴얼 처리된 계정이 아님"),
        AUTH_NOT_FOUND_PIN_SETUP_CACHE  (HttpStatus.NOT_FOUND, "PIN 설정정보 없음"),
        AUTH_MISMATCH_PIN_SETUP_INFO    (HttpStatus.BAD_REQUEST, "PIN 설정정보 일치하지 않음"),
        AUTH_INVALID_INFLOW_ROUTE_TYPE  (HttpStatus.BAD_REQUEST, "Secure PIN 설정 불가 계정"),
        AUTH_ENCRYPT_PIN_ERROR          (HttpStatus.BAD_REQUEST, "Secure PIN 설정 오류"),
        AUTH_MISMATCH_PASSWORD          (HttpStatus.BAD_REQUEST, "패스워드 매칭 오류"),
        AUTH_EXIST_USER_ID              (HttpStatus.BAD_REQUEST, "사용 중인 ID"),
        AUTH_DECRYPT_PASSWORD_ERROR     (HttpStatus.BAD_REQUEST, "패스워드 요청 오류"),
        AUTH_INVALID_PASSWORD           (HttpStatus.BAD_REQUEST, "잘못된 패스워드"),
        AUTH_NO_CONFIRM_ACCOUNT         (HttpStatus.BAD_REQUEST, "가입 승인되지 않은 계정"),
        AUTH_INVALID_ACCOUNT_STATUS     (HttpStatus.BAD_REQUEST, "잘못된 계정 상태코드"),
        AUTH_REMOVE_CACHE_ERROR         (HttpStatus.INTERNAL_SERVER_ERROR, "세션정보 삭제 오류"),
        AUTH_PIN_RESET_NO_CERT          (HttpStatus.BAD_REQUEST, "본인인증 진행 후 재설정 가능합니다."),
        AUTH_PIN_RESET_NO_CERT_FINISH   (HttpStatus.BAD_REQUEST, "본인인증을 먼저 완료해야 합니다."),
        AUTH_PIN_RESET_KEY_ERROR        (HttpStatus.INTERNAL_SERVER_ERROR, "키 생성에 오류가 발생하였습니다."),
        AUTH_EXPIRED_TOKEN              (HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
        AUTH_INVALID_USER_ID            (HttpStatus.BAD_REQUEST, "잘못된 사용자 정보입니다."),
        /****************************** 인증 ******************************/

        /****************************** 대출 ******************************/
        INVALID_COUNSEL_STEP            (HttpStatus.BAD_REQUEST, "유효하지 않은 상담 단계"),
        INVALID_USAGE_TYPE              (HttpStatus.BAD_REQUEST, "유효하지 않은 대출 용도"),
        INVALID_HOPE_PRICE              (HttpStatus.BAD_REQUEST, "유효하지 않은 최대 대출 희망금액"),
        INVALID_MORTGAGE_DEBT_AMT       (HttpStatus.BAD_REQUEST, "유효하지 않은 담보 부채 금액"),
        INVALID_REPAY_MORTGAGE_DEBT_AMT (HttpStatus.BAD_REQUEST, "유효하지 않은 담보 부채 상환 금액"),
        INVALID_CREDIT_DEBT_AMT         (HttpStatus.BAD_REQUEST, "유효하지 않은 신용 부채 금액"),
        INVALID_REPAY_CREDIT_DEBT_AMT   (HttpStatus.BAD_REQUEST, "유효하지 않은 신용 부채 상환 금액"),

        INVALID_USER_MATCH              (HttpStatus.FORBIDDEN, "회원 정보와 상담 정보 맞지 않음"),

        NOT_FOUND_COUNSEL               (HttpStatus.NOT_FOUND, "상담 정보 없음"),
        NOT_FOUND_CONDITIONS            (HttpStatus.NOT_FOUND, "상담 조건 없음"),
        CANNOT_FIND_RECEIVER            (HttpStatus.NOT_FOUND, "메일 수신대상을 확인할 수 없습니다."),

        CANNOT_CALCULATE_LTV            (HttpStatus.BAD_REQUEST, "대출 한도를 계산할 수 없습니다."),
        NOT_SUPPORTED_LOAN_RATE         (HttpStatus.BAD_REQUEST, "해당 상품은 지원하지 않는 대출금리입니다."),
        /****************************** 대출 ******************************/

        /****************************** 대출 ******************************/
        COUNSEL_LIMIT_MIN_AGE           (HttpStatus.BAD_REQUEST, "만 19세 이상부터 대출 비교/신청이 가능합니다."),
        COUNSEL_INVALID_FIN_CO_NO       (HttpStatus.BAD_REQUEST, "잘못된 금융사 코드입니다."),
        COUNSEL_INVALID_AGENCY_INFO     (HttpStatus.BAD_REQUEST, "대출지점 선택에 오류가 있습니다."),
        COUNSEL_CREATE_ERROR            (HttpStatus.INTERNAL_SERVER_ERROR, "대출상담정보 생성에 실패하였습니다."),
        COUNSEL_PRODUCT_EMPTY           (HttpStatus.NOT_FOUND, "조건에 맞는 상품을 찾을 수 없습니다."),
        COUNSEL_CPI_REQUEST_ERROR       (HttpStatus.INTERNAL_SERVER_ERROR, "금융사 한도조회 요청에 실패하였습니다."),
        COUNSEL_CPI_LOAN_ERROR          (HttpStatus.INTERNAL_SERVER_ERROR, "금융사 대출신청 요청에 실패하였습니다."),
        COUNSEL_NOT_FOUND               (HttpStatus.NOT_FOUND, "상담 정보를 찾을 수 없습니다."),
        COUNSEL_INVALID_USER            (HttpStatus.BAD_REQUEST, "사용자의 상담정보가 아닙니다."),
        COUNSEL_INVALID_STATUS          (HttpStatus.BAD_REQUEST, "상담 상태 오류"),
        COUNSEL_NOT_FOUND_RATE          (HttpStatus.NOT_FOUND, "상품 계산정보가 없습니다."),
        COUNSEL_NOT_FOUND_KB_PRICE      (HttpStatus.NOT_FOUND, "KB 시세 데이터를 찾을 수 없습니다."),
        COUNSEL_NOT_FOUND_PRODUCT       (HttpStatus.NOT_FOUND, "대출상품 정보를 찾을 수 없습니다."),
        COUNSEL_NOT_FOUND_RPA_HISTORY   (HttpStatus.NOT_FOUND, "사용자 자동화 프로세스 로그를 찾을 수 없습니다."),
        COUNSEL_RPA_HISTORY_CREATE_ERROR   (HttpStatus.INTERNAL_SERVER_ERROR, "사용자 자동화 프로세스 로그 생성에 실패하였습니다."),
        COUNSEL_NOT_FOUND_PRODUCT_PRIME (HttpStatus.NOT_FOUND, "상품 우대금리를 찾을 수 없습니다."),
        COUNSEL_APPLY_PRIME_RATE_OVER   (HttpStatus.BAD_REQUEST, "선택하신 우대금리가 상품 최대 우대금리보다 초과되었습니다."),
        COUNSEL_INVALID_ROUTE_TYPE      (HttpStatus.BAD_REQUEST, "잘못된 대출 신청 코드입니다."),
        COUNSEL_INVALID_OUTLINK_URL     (HttpStatus.INTERNAL_SERVER_ERROR, "대출 신청 URL이 존재하지 않습니다."),
        COUNSEL_MISMATCH_COUNSELOR      (HttpStatus.NOT_FOUND, "상담사 매칭에 실패하였습니다."),
        /****************************** 대출 ******************************/

        /****************************** 회원 ******************************/
        NOT_FOUND_USER                      (HttpStatus.NOT_FOUND, "일치하는 회원 정보를 찾을 수 없습니다."),
        CANNOT_REGISTRATION_UNMATCHED_PIN   (HttpStatus.BAD_REQUEST, "입력한 PIN이 일치하지 않아 등록할 수 없습니다."),
        CANNOT_DECODE_PIN                   (HttpStatus.BAD_REQUEST, "PIN 복호화 실패"),
        CANNOT_DECODE_RESIDENT_NUMBER       (HttpStatus.BAD_REQUEST, "주민등록번호 복호화에 실패하였습니다."),
        INVALID_USER_BIRTH                  (HttpStatus.BAD_REQUEST, "사용자 생년월일이 유효하지 않습니다."),
        INVALID_RESIDENT_ID                 (HttpStatus.BAD_REQUEST, "유효하지 않은 주민등록번호 입니다."),

        // 제휴사
        CANNOT_SIGNUP_AFFILIATED_USER       (HttpStatus.BAD_REQUEST, "페이지에 접근할 수 없습니다."),
        /****************************** /회원 ******************************/

        /****************************** 부동산 ******************************/
        NOT_FOUND_APARTMENT             (HttpStatus.NOT_FOUND, "물건지 정보를 확인할 수 없습니다."),
        EXCEEDED_APARTMENT              (HttpStatus.BAD_REQUEST, "이미 모든 물건지가 등록되어있습니다."),
        /****************************** /부동산 ******************************/

        /****************************** 리워드 ******************************/
        NOT_FOUND_USER_REWARD_INFO      (HttpStatus.NOT_FOUND, "리워드 정보를 확인할 수 없습니다."),
        NOT_FOUND_RECOMMENDER_CODE      (HttpStatus.NOT_FOUND, "유효하지 않은 추천인 코드 입니다."),
        NOT_CERT_USER                   (HttpStatus.NOT_FOUND, "포인트 사용을 하시려면 본인 인증을 하셔야 합니다."),
        NOT_REWARD_TERMS                (HttpStatus.NOT_FOUND, "포인트 사용을 하시려면 관련 약관 동의가 필요합니다."),
        NOT_ADULT_USER                  (HttpStatus.NOT_FOUND, "미성년자는 포인트 사용을 할 수 없습니다."),
        NOT_MATCH_POINT                 (HttpStatus.BAD_REQUEST, "상품권 신청은 만원 단위로 가능합니다.\n확인 후 다시 신청해 주세요."),
        TODAY_MAX_POINT                 (HttpStatus.BAD_REQUEST, "하루 최대 5만 포인트까지만 상품권 교환 신청할 수 있습니다."),
        NOT_ENOUGH_POINT                (HttpStatus.BAD_REQUEST, "사용 가능한 포인트가 부족합니다."),
        MAX_ACCUMULATE_POINT            (HttpStatus.BAD_REQUEST, "해당 적립 포인트의 최대 적립 횟수에 도달하였습니다."),
        MAX_PERIOD_ACCUMULATE_POINT     (HttpStatus.BAD_REQUEST, "해당 적립 포인트의 기간별 최대 적립 횟수에 도달하였습니다."),
        /****************************** 리워드 ******************************/

        /***************************** 커뮤니티 *****************************/
        COMMUNITY_INVALID_WORDS               (HttpStatus.BAD_REQUEST, "사용할 수 없는 단어가 포함되어 있습니다."),
        COMMUNITY_ALREADY_USED_NICKNAME1      (HttpStatus.BAD_REQUEST, "이미 사용중인 닉네임입니다."),
        COMMUNITY_ALREADY_USED_NICKNAME2      (HttpStatus.BAD_REQUEST, "이미 사용 중인 닉네임입니다."),
        COMMUNITY_NICKNAME_NOT_CONFIRMED      (HttpStatus.BAD_REQUEST, "닉네임 중복 확인을 해주세요."),
        COMMUNITY_NICKNAME_NOT_CONFIRM        (HttpStatus.BAD_REQUEST, "닉네임 중복확인을 해주세요."),
        COMMUNITY_NICKNAME_DIFFERENT          (HttpStatus.BAD_REQUEST, "증복확인한 닉네임과 다른 닉네임입니다."),
        COMMUNITY_NICKNAME_CONFIRM_EXPIRED    (HttpStatus.BAD_REQUEST, "닉네임 중복확인을 다시 해주세요."),
        COMMUNITY_NICKNAME_EMPTY              (HttpStatus.BAD_REQUEST, "커뮤니티 사용자가 아닙니다."),
        COMMUNITY_EMPTY_BESTPICK              (HttpStatus.NOT_FOUND, "베스트픽 게시글이 없습니다."),
        COMMUNITY_NOT_FOUND_POST              (HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."),
        COMMUNITY_NOT_FOUND_COMMENT           (HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."),
        COMMUNITY_NOT_FOUND_REPLY             (HttpStatus.NOT_FOUND, "답글을 찾을 수 없습니다."),
        COMMUNITY_POST_NEW_ERROR              (HttpStatus.INTERNAL_SERVER_ERROR, "게시글 작성 중 오류가 발생하였습니다."),
        COMMUNITY_POST_NOT_OWNER              (HttpStatus.UNAUTHORIZED, "게시글 접근 권한이 없습니다."),
        COMMUNITY_POST_TITLE_ERROR            (HttpStatus.BAD_REQUEST, "게시글 제목에 오류가 있습니다."),
        COMMUNITY_POST_CONTENTS_ERROR         (HttpStatus.BAD_REQUEST, "게시글 내용에 오류가 있습니다."),
        COMMUNITY_POST_COMMENT_ERROR          (HttpStatus.BAD_REQUEST, "댓글 내용에 오류가 있습니다."),
        COMMUNITY_POST_COMMENT_NOT_OWNER      (HttpStatus.UNAUTHORIZED, "댓글 접근 권한이 없습니다."),
        COMMUNITY_POST_REPLY_ERROR            (HttpStatus.BAD_REQUEST, "답글 내용에 오류가 있습니다."),
        COMMUNITY_POST_REPLY_NOT_OWNER        (HttpStatus.UNAUTHORIZED, "답글 접근 권한이 없습니다."),
        COMMUNITY_REPORT_REASON_ERROR         (HttpStatus.BAD_REQUEST, "신고사유를 선택해 주세요."),
        COMMUNITY_REPORT_REASON_INPUT_ERROR   (HttpStatus.BAD_REQUEST, "신고사유를 입력해 주세요."),
        COMMUNITY_REPORT_NO_SELF_WRITING      (HttpStatus.BAD_REQUEST, "본인 글을 신고할 수 없습니다."),
        COMMUNITY_REPORT_NO_SELF_USER         (HttpStatus.BAD_REQUEST, "자기 자신을 신고할 수 없습니다."),
        COMMUNITY_ALREDY_DISABLE_POST         (HttpStatus.BAD_REQUEST, "이미 비활성화된 게시글입니다."),
        COMMUNITY_ALREDY_DISABLE_COMMENT      (HttpStatus.BAD_REQUEST, "이미 비활성화된 댓글입니다."),
        COMMUNITY_ALREDY_DISABLE_REPLY        (HttpStatus.BAD_REQUEST, "이미 비활성화된 답글입니다."),
        COMMUNITY_COMMENT_NOT_ALLOWED         (HttpStatus.BAD_REQUEST, "댓글 작성이 허용되지 않은 게시글입니다."),
        COMMUNITY_LIKE_NOT_ALLOWED            (HttpStatus.BAD_REQUEST, "좋아요 버튼이 허용되지 않은 게시글입니다."),
        COMMUNITY_NO_SELF_LIKE                (HttpStatus.BAD_REQUEST, "본인 게시글에는 좋아요를 누를 수 없습니다."),
        COMMUNITY_ERROR_MAX                   (HttpStatus.SERVICE_UNAVAILABLE, "UNKOWN ERROR"),
        /***************************** 커뮤니티 *****************************/

        /***************************** 프로모션 *****************************/
        PROMOTION_INVALID_CODE                (HttpStatus.BAD_REQUEST, "잘못된 프로모션 코드입니다."),
        PROMOTION_UNAVAILABLE_CODE            (HttpStatus.BAD_REQUEST, "사용이 불가능한 코드입니다."),
        PROMOTION_EXPIRED_CODE                (HttpStatus.BAD_REQUEST, "기간이 만료된 코드입니다."),
        PROMOTION_NOT_CERTIFICATION_USER      (HttpStatus.BAD_REQUEST, "본인인증이 완료되지 않은 계정입니다."),
        PROMOTION_INVALID_JOIN_DATE           (HttpStatus.BAD_REQUEST, "해당 가입일자에 사용할 수 없는 코드입니다."),
        PROMOTION_ALREADY_REGISTER_CODE       (HttpStatus.BAD_REQUEST, "이미 등록된 코드입니다."),
        PROMOTION_ANOTHER_REGISTER_CODE       (HttpStatus.BAD_REQUEST, "이미 다른 코드로 등록된 계정입니다."),
        /***************************** 프로모션 *****************************/
	
	    /****************************** 약관 ******************************/
	    REQUIED_TERMS_AGREE                 (HttpStatus.BAD_REQUEST, "약관 동의 필요"),
        INVALID_TERMS_TYPE                  (HttpStatus.BAD_REQUEST, "유효하지 않은 약관 타입"),
        INVALID_TERMS_PREFIX                (HttpStatus.BAD_REQUEST, "유효하지 않은 약관 코드"),
        INVALID_TERMS_VERSION               (HttpStatus.BAD_REQUEST, "유효하지 않은 약관 버전"),
        NOT_FOUND_TERMS_INFO                (HttpStatus.NOT_FOUND, "찾을 수 없는 약관"),
        /****************************** 약관 ******************************/

        /***************************** APP 버전관리 *****************************/
        APP_VERSION_NOT_FOUND               (HttpStatus.NOT_FOUND, "App 버전 정보를 찾을 수 없습니다."),
        APP_VERSION_VERSION_ERROR           (HttpStatus.BAD_REQUEST, "APP 버전 정보가 형식에 맞지 않습니다."),
        APP_VERSION_TYPE_ERROR              (HttpStatus.BAD_REQUEST, "APP TYPE 정보가 형식에 맞지 않습니다."),
        APP_VERSION_OS_TYPE_ERROR           (HttpStatus.BAD_REQUEST, "APP OS TYPE 정보가 형식에 맞지 않습니다."),
        APP_VERSION_MIN_VERSION_ERROR       (HttpStatus.BAD_REQUEST, "APP 최소 허용 버전 정보가 형식에 맞지 않습니다."),
        APP_VERSION_RESOURCE_VERSION_ERROR  (HttpStatus.BAD_REQUEST, "리소스 버전 정보가 형식에 맞지 않습니다."),
        APP_VERSION_BEGIN_DATE_ERROR        (HttpStatus.BAD_REQUEST, "버전 시작일 정보가 형식에 맞지 않습니다."),
        APP_VERSION_END_DATE_ERROR          (HttpStatus.BAD_REQUEST, "버전 종료일 정보가 형식에 맞지 않습니다."),
        APP_VERSION_DESCRIPTION_ERROR       (HttpStatus.BAD_REQUEST, "버전 설명 정보는 공백이 될 수 없습니다."),
        APP_VERSION_API_URL_ERROR           (HttpStatus.BAD_REQUEST, "접속 API 주소 정보는 공백이 될 수 없습니다."),
        APP_VERSION_CREATE_ERROR            (HttpStatus.BAD_REQUEST, "이미 존재하는 APP 버전 정보 입니다."),
        APP_VERSION_CREATE_DATE_ERROR       (HttpStatus.BAD_REQUEST, "버전 시작일은 현재 시간 이 후 부터 가능합니다."),
        APP_VERSION_BEGIN_END_ERROR         (HttpStatus.BAD_REQUEST, "버전 시작일은 종료일보다 이전 이어야 합니다."),
        APP_VERSION_EXIST_BEGIN_ERROR       (HttpStatus.BAD_REQUEST, "버전 시작일은 이전 버전의 시작일 보다 이 후 여야 합니다."),
        APP_VERSION_VER_COMPARE_ERROR       (HttpStatus.BAD_REQUEST, "APP 버전은 이전 버전보다 커야 합니다."),
        APP_VERSION_VER_MIN_ERROR           (HttpStatus.BAD_REQUEST, "APP 버전은 최소 허용 버전보다 커야 합니다."),
        APP_VERSION_STORE_URL_ERROR         (HttpStatus.BAD_REQUEST, "담비 사용자 앱은 스토어 URL 정보가 있어야 합니다."),
        /***************************** APP 버전관리 *****************************/

        /****************************** 알림 설정 ******************************/
        INVALID_NOTIFICATION_SEND_TYPE      (HttpStatus.BAD_REQUEST, "알림 설정에 맞지 않는 발송 형식 입니다."),
        NOT_EXIST_NOTIFICATION_ID           (HttpStatus.BAD_REQUEST, "알림 정보가 존재하지 않습니다."),
        /****************************** 약관 ******************************/

        /***************************** okcert *****************************/
        OKCERT_INVALID_ACCESS_KEY           (HttpStatus.BAD_REQUEST, "잘못된 액세스 요청"),
        OKCERT_NAME_DECRYPT_ERROR           (HttpStatus.BAD_REQUEST, "사용자 이름 오류"),
        OKCERT_MOBILE_DECRYPT_ERROR         (HttpStatus.BAD_REQUEST, "사용자 전화번호 오류"),
        OKCERT_INVALID_GENDER_CODE          (HttpStatus.BAD_REQUEST, "잘못된 성별 코드"),
        OKCERT_INVALID_LFCODE               (HttpStatus.BAD_REQUEST, "잘못된 내외국인 코드"),
        OKCERT_INVALID_TELECOM_CODE         (HttpStatus.BAD_REQUEST, "잘못된 통신사 코드"),
        OKCERT_INVALID_REUQEST_REASON_CODE  (HttpStatus.BAD_REQUEST, "잘못된 요청사유 코드"),
        OKCERT_INVALID_RESEND_SEQ           (HttpStatus.BAD_REQUEST, "재전송 코드 오류"),
        OKCERT_NOT_FOUND_PROVIDER_INFO      (HttpStatus.NOT_FOUND, "로그인 정보 없음"),
        OKCERT_INVALID_INFLOW_ROUTE_CODE    (HttpStatus.BAD_REQUEST, "잘못된 유입경로"),
        OKCERT_NOT_SUPPORT_USER_TYPE        (HttpStatus.BAD_REQUEST, "지원하지 않는 계정타입"),
        OKCERT_EXIST_PROVIDER_ACCOUNT       (HttpStatus.BAD_REQUEST, "이미 본인인증한 계정이 있음"),
        OKCERT_ERROR                        (HttpStatus.BAD_REQUEST, "본인인증 오류"),
        /***************************** okcert *****************************/

        /***************************** advert *****************************/
        ADVERT_INVALID_AREA_TYPE            (HttpStatus.BAD_REQUEST, "잘못된 광고 영역 요청입니다."),
        ADVERT_NOT_FOUND_AREA               (HttpStatus.NOT_FOUND, "해당 영역에 광고가 없습니다."),
        ADVERT_NOT_FOUND_ID                 (HttpStatus.NOT_FOUND, "광고를 찾을 수 없습니다."),
        ADVERT_NOT_PROCEEDING               (HttpStatus.BAD_REQUEST, "진행 중이 아닌 광고입니다."),
        ADVERT_INVALID_PERIOD               (HttpStatus.BAD_REQUEST, "광고 진행시간이 아닙니다."),
        /***************************** advert *****************************/

        /***************************** kcb allcredit *****************************/
        ALLCREDIT_UNREGISTERED              (HttpStatus.BAD_REQUEST, "KCB 신용정보조회서비스 미가입 계정입니다."),
        ALLCREDIT_ANOTHER_ENV_JOIN          (HttpStatus.BAD_REQUEST, "다른 환경에서 가입한 계정입니다."),
        ALLCREDIT_ANOTHER_INFLOW_JOIN       (HttpStatus.BAD_REQUEST, "다른 가입경로로 가입된 계정입니다."),
        ALLCREDIT_ALREADY_JOIN_SERVICE      (HttpStatus.BAD_REQUEST, "이미 가입된 서비스 입니다."),
        ALLCREDIT_ALREADY_WITHDRAW_SERVICE  (HttpStatus.BAD_REQUEST, "이미 탈퇴한 서비스 입니다."),
        ALLCREDIT_NOT_AGREE_TERMS           (HttpStatus.BAD_REQUEST, "KCB 신용정보조회서비스 필수약관 동의가 필요합니다."),
        ALLCREDIT_UPDATE_TERMS              (HttpStatus.BAD_REQUEST, "KCB 신용정보조회서비스 필수약관 재동의가 필요합니다."),
        ALLCREDIT_INCREASE_UPDATE_TERMS     (HttpStatus.BAD_REQUEST, "KCB 신용올리기 필수약관 재동의가 필요합니다."),
        ALLCREDIT_INCREASE_NOT_AGREE_TERMS  (HttpStatus.BAD_REQUEST, "KCB 신용올리기 필수약관 동의가 필요합니다."),
        ALLCREDIT_INCREASE_DAILY_ERROR      (HttpStatus.BAD_REQUEST, "KCB 신용올리기는 하루에 한 번만 가능합니다."),
        ALLCREDIT_NOT_FOUND_CREDIT_INFO     (HttpStatus.NOT_FOUND, "신용조회 이력이 없습니다."),
        //ALLCREDIT_JOIN_FAILED               (HttpStatus.BAD_REQUEST, "KCB 신용정보조회서비스 가입에 실패하였습니다."),
        ALLCREDIT_JOIN_FAILED               (HttpStatus.BAD_REQUEST, "KCB 신용정보조회서비스에 연결할 수 없어,<br>대출비교 서비스를 이용할 수 없습니다.<br>잠시 후 다시 시도해 주세요"),
        ALLCREDIT_WITHDRAW_FAILED           (HttpStatus.BAD_REQUEST, "KCB 신용정보조회서비스 탈퇴에 실패하였습니다."),
        ALLCREDIT_PERSONAL_INFO_URL_ERROR   (HttpStatus.BAD_REQUEST, "KCB 신용정보조회서비스 개인 신용보고서 URL 획득에 실패하였습니다."),
        ALLCREDIT_STATISTICS_URL_ERROR      (HttpStatus.BAD_REQUEST, "KCB 신용정보조회서비스 신용통계 URL 획득에 실패하였습니다."),
        ALLCREDIT_INCREASE_URL_ERROR        (HttpStatus.BAD_REQUEST, "KCB 신용올리기 결과 URL 획득에 실패하였습니다."),
        ALLCREDIT_PERSONAL_INFO_ERROR       (HttpStatus.BAD_REQUEST, "KCB 신용정보조회서비스 개인 신용보고서 조회에 실패하였습니다."),
        ALLCREDIT_STATISTICS_ERROR          (HttpStatus.BAD_REQUEST, "KCB 신용정보조회서비스 신용통계 조회에 실패하였습니다."),
        ALLCREDIT_INCREASE_INFO_ERROR       (HttpStatus.BAD_REQUEST, "KCB 신용올리기 결과 조회에 실패하였습니다."),
        ALLCREDIT_INCREASE_NTS_ERROR        (HttpStatus.BAD_REQUEST, "KCB 신용올리기 국세청 소득정보 조회에 실패하였습니다."),
        ALLCREDIT_INCREASE_NHIS_ERROR       (HttpStatus.BAD_REQUEST, "KCB 신용올리기 건강보험 조회에 실패하였습니다."),
        ALLCREDIT_INCREASE_NPS_ERROR        (HttpStatus.BAD_REQUEST, "KCB 신용올리기 국민연금 조회에 실패하였습니다."),
        ALLCREDIT_INCREASE_TIME_ERROR       (HttpStatus.BAD_REQUEST, "KCB 신용올리기 가능 시간이 아닙니다."),
        ALLCREDIT_INCREASE_AGREE_ERROR      (HttpStatus.BAD_REQUEST, "KCB 신용올리기 공공정보 동의처리에 실패하였습니다."),
        ALLCREDIT_INCREASE_MOIS_ERROR       (HttpStatus.BAD_REQUEST, "KCB 신용올리기 행정안전부 통신 오류입니다."),
        ALLCREDIT_CREDIT_UPDATE_NOT_FOUND   (HttpStatus.NOT_FOUND, "신용정보 변동 이력이 없습니다."),
        ALLCREDIT_SERVICE_ERROR             (HttpStatus.INTERNAL_SERVER_ERROR, "KCB 신용정보조회서비스 통신에 오류가 발생하였습니다.")
        /***************************** kcb allcredit *****************************/
    }

    // 성공이 아닌 실패 결과에 대한 custom response 정의
    // 나중에는 사용하지 않는 방향으로 처리
    class CustomResCode {
        var resCode: ResCode = ResCode.INTERVAL_SERVER_ERROR
        var errorCode: Int = Response.DefaultEmptyCode
        var responseData: Any? = null

        constructor()

        constructor(resCode: ResCode, responseData: Any? = null) {
            this.resCode = resCode
            this.errorCode = this.getErrorCode(resCode)
            this.responseData = responseData
        }

        constructor(resCode: ResCode, errorCode: Int, responseData: Any? = null) {
            this.resCode = resCode
            this.errorCode = errorCode
            this.responseData = responseData
        }

        private fun getErrorCode(resCode: ResCode): Int {
            return when (resCode) {
                // sjyoo : native 요청으로 별도 코드로 분리 처리
                // Todo: 현재 response 형식이 중구난방이라 우선 개발 우선으로 처리하였으나, 추후 refactoring 해야함
                ResCode.COMMUNITY_INVALID_WORDS -> 50001
                ResCode.COMMUNITY_ALREADY_USED_NICKNAME1 -> 50002
                ResCode.COMMUNITY_ALREADY_USED_NICKNAME2 -> 50003
                ResCode.COMMUNITY_NICKNAME_NOT_CONFIRMED -> 50004
                ResCode.COMMUNITY_NICKNAME_NOT_CONFIRM -> 50005
                ResCode.COMMUNITY_NICKNAME_DIFFERENT -> 50006
                ResCode.COMMUNITY_NICKNAME_CONFIRM_EXPIRED -> 50007
                ResCode.COMMUNITY_EMPTY_BESTPICK -> 50008
                ResCode.COMMUNITY_NOT_FOUND_POST -> 50009
                ResCode.COMMUNITY_NOT_FOUND_COMMENT -> 50010
                ResCode.COMMUNITY_NOT_FOUND_REPLY -> 50011
                ResCode.COMMUNITY_POST_NEW_ERROR -> 50012
                ResCode.COMMUNITY_POST_NOT_OWNER -> 50013
                ResCode.COMMUNITY_POST_TITLE_ERROR -> 50014
                ResCode.COMMUNITY_POST_CONTENTS_ERROR -> 50015
                ResCode.COMMUNITY_POST_COMMENT_ERROR -> 50016
                ResCode.COMMUNITY_POST_COMMENT_NOT_OWNER -> 50017
                ResCode.COMMUNITY_POST_REPLY_ERROR -> 50018
                ResCode.COMMUNITY_POST_REPLY_NOT_OWNER -> 50019
                ResCode.COMMUNITY_REPORT_REASON_ERROR -> 50020
                ResCode.COMMUNITY_REPORT_REASON_INPUT_ERROR -> 50021
                ResCode.COMMUNITY_REPORT_NO_SELF_WRITING -> 50022
                ResCode.COMMUNITY_REPORT_NO_SELF_USER -> 50023
                ResCode.COMMUNITY_ALREDY_DISABLE_POST -> 50024
                ResCode.COMMUNITY_ALREDY_DISABLE_COMMENT -> 50025
                ResCode.COMMUNITY_ALREDY_DISABLE_REPLY -> 50026
                ResCode.COMMUNITY_COMMENT_NOT_ALLOWED -> 50027
                ResCode.COMMUNITY_LIKE_NOT_ALLOWED -> 50028
                ResCode.COMMUNITY_NO_SELF_LIKE -> 50029
                // in ResCode.COMMUNITY_INVALID_WORDS..ResCode.COMMUNITY_ERROR_MAX -> 50001
                else -> resCode.httpStatus.value()
            }
        }
    }

    object AgentUser {
        const val ApartmentMatchedStatus: String = "MATCHED"
        const val ApartmentNotMatchedStatus: String = "NOT_MATCHED"
        const val ApartmentMaxCount: Int = 5
    }

    object MAIL {
        enum class TEMPLATE(val bankCode: String?, val loanType: AppTypes.Product.LoanType, val path: String) {
            DEFAULT_MORTGAGE(null, AppTypes.Product.LoanType.M, "mail/loan_mortgage_attach.html"),
            DEFAULT_RENT(null, AppTypes.Product.LoanType.R, "mail/loan_rent_attach.html"),
            DEFAULT_BUSINESS(null, AppTypes.Product.LoanType.B, "mail/loan_business_attach.html"),

            // 메일 템플릿이 구분되어 있는 경우 아래 추가 기술
            HANHWA_MORTGAGE(FinancialCompany.HANHWA, AppTypes.Product.LoanType.M, "mail/loan_mortgage_hanwha_attach.html"),
            NHCAPITAL_BUSINESS(FinancialCompany.NHCAPITAL, AppTypes.Product.LoanType.B, "mail/loan_business_nhcapital_attach.html"),
            PEOPLEFUND_MORTGAGE(FinancialCompany.PEOPLEFUND, AppTypes.Product.LoanType.M, "mail/loan_mortgage_peoplefund_attach.html");

            fun path() = path
        }

        enum class TEMPLATE_V2(val finCoNo: String?, val loanType: String, val path: String) {
            DEFAULT_MORTGAGE(null, AppConst.Counsel.LoanType.MORTGAGE.code, "mail/loan_mortgage_attach_v2.html"),
            DEFAULT_RENT(null, AppConst.Counsel.LoanType.RENT.code, "mail/loan_rent_attach_v2.html"),

            // 메일 템플릿이 구분되어 있는 경우 아래 추가 기술
            HANHWA_MORTGAGE(FinancialCompany.HANHWA, AppConst.Counsel.LoanType.MORTGAGE.code, "mail/loan_mortgage_hanwha_attach_v2.html"),
            NHCAPITAL_BUSINESS(FinancialCompany.NHCAPITAL, AppConst.Counsel.LoanType.MORTGAGE.code, "mail/loan_business_nhcapital_attach_v2.html"),
            PEOPLEFUND_MORTGAGE(FinancialCompany.PEOPLEFUND, AppConst.Counsel.LoanType.MORTGAGE.code, "mail/loan_mortgage_peoplefund_attach_v2.html"),
            EIGHT_PERCENT_MORTGAGE(FinancialCompany.EIGHT_PERCENT, AppConst.Counsel.LoanType.MORTGAGE.code, "mail/loan_mortgage_eight_percent_attach_v2.html");

            fun path() = path
        }

        @Deprecated("삭제 예정")
        private fun findByBankCodeAndLoanType(backCode: String, loanType: AppTypes.Product.LoanType): TEMPLATE? {
            return TEMPLATE.values().find {
                it.bankCode.equals(backCode) && it.loanType == loanType
            }
        }

        private fun findByBankCodeAndLoanTypeV2(finCoNo: String, loanType: String): TEMPLATE_V2? {
            return TEMPLATE_V2.values().find {
                it.finCoNo.equals(finCoNo) && it.loanType == loanType
            }
        }

        @Deprecated("삭제 예정")
        private fun findDefaultByLoanType(loanType: AppTypes.Product.LoanType): TEMPLATE? {
            return TEMPLATE.values().find {
                it.bankCode == null && it.loanType == loanType
            }
        }

        private fun findDefaultByLoanTypeV2(loanType: String): TEMPLATE_V2? {
            return TEMPLATE_V2.values().find {
                it.finCoNo == null && it.loanType == loanType
            }
        }

        @Deprecated("삭제 예정")
        fun template(bankCode: String, loanType: AppTypes.Product.LoanType): TEMPLATE? =
            findByBankCodeAndLoanType(bankCode, loanType)
            ?: findDefaultByLoanType(loanType)

        fun templateV2(finCoNo: String, loanType: String): TEMPLATE_V2? =
            findByBankCodeAndLoanTypeV2(finCoNo, loanType)
                ?: findDefaultByLoanTypeV2(loanType)

        const val PAYBOOC_HPID_LENGTH = 64
    }
}
