package com.moin.api.domain.user.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "login_history")
@Comment("로그인 이력 테이블")
class LoginHistory(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Comment("로그인 이력 IDX")
    var loginHistoryIdx: Long = 0L,

    @Column(nullable = false)
    @Comment("회원 IDX")
    var userIdx: Long = 0L,

    @Column(nullable = false, length = 200)
    @Comment("인증 토큰 코드")
    var authTokenCode: String = "",

    @Column(nullable = false)
    @Comment("인증토큰 만료일시")
    var authTokeExpireDtm: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    @Comment("등록일시")
    var regDtm: LocalDateTime = LocalDateTime.now(),

    ) {

    constructor(
        userIdx: Long,
        authTokenCode: String,
        authTokeExpireDtm: LocalDateTime
    ) : this(
        loginHistoryIdx = 0L,
        userIdx = userIdx,
        authTokenCode = authTokenCode,
        authTokeExpireDtm = authTokeExpireDtm,
        regDtm = LocalDateTime.now()
    )
}