package com.moin.api.domain.user.entity

import com.fin.best.bestfin.api.component.constants.AppConst
import com.moin.api.component.crypto.StringCryptoConverter
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Entity
@Table(name = "users")
@Comment("회원 테이블")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Comment("회원 IDX")
    var userIdx: Long = 0L,

    @Column(nullable = false, length = 100, unique = true)
    @Comment("회원 ID")
    var userId: String = "",

    @Column(nullable = false, length = 200)
    @Comment("비밀번호")
    var userPassword: String = "",

    @Column(nullable = false, length = 50)
    @Comment("이름")
    @field:Convert(converter = StringCryptoConverter::class)
    var name: String = "",

    @Column(nullable = false, length = 20)
    @Comment("ID 타입 (REG_NO, BUSINESS_NO)")
    var idType: String = "",

    @Column(nullable = false, length = 200)
    @Comment("ID 값 (주민등록번호, 사업자등록번호) -idType 컬럼 값에 따라 형식 변경")
    @field:Convert(converter = StringCryptoConverter::class)
    var idValue: String = "",

    @Column(nullable = false, length = 20)
    var role: String = AppConst.User.Role.USER.code,

    @Comment("최신 로그인 이력 IDX")
    var latestLoginHistoryIdx: Long? = null,

    @Column(nullable = false)
    @Comment("등록일시")
    var regDtm: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    @Comment("수정일시")
    var modDtm: LocalDateTime = LocalDateTime.now(),

    ) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(this.role))
    }

    override fun getPassword(): String {
        return this.userPassword
    }

    override fun getUsername(): String {
        return this.userId
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    /* 회원 데이터 생성자 */
    constructor(
        userId: String,
        userPassword: String,
        name: String,
        idType: String,
        idValue: String,
        role: String
    ) : this(
        userIdx = 0,
        userId = userId,
        userPassword = userPassword,
        name = name,
        idType = idType,
        idValue = idValue,
        role = role,
        regDtm = LocalDateTime.now(),
        modDtm = LocalDateTime.now()
    )
}