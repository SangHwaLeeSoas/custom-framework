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
    val loginHistoryIdx: Long,

    @Column(nullable = false, length = 200)
    @Comment("인증 토큰 코드")
    val authTokenCode: String,

    @Column(nullable = false)
    @Comment("인증토큰 만료일시")
    val authTokeExpireDtm: LocalDateTime,

    @Column(nullable = false)
    @Comment("등록일시")
    val regDtm: LocalDateTime = LocalDateTime.now(),
) {

}