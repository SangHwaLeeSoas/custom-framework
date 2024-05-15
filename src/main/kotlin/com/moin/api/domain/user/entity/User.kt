package com.moin.api.domain.user.entity

import com.fin.best.bestfin.api.component.constants.AppConst
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
    val userIdx: Long = 0,

    @Column(nullable = false, length = 100)
    @Comment("회원 ID")
    val userId: String,

    @Column(nullable = false, length = 200)
    @Comment("비밀번호")
    val userPassword: String,

    @Column(nullable = false, length = 50)
    @Comment("이름")
    val name: String,

    @Column(nullable = false, length = 20)
    @Comment("ID 타입 (REG_NO, BUSINESS_NO)")
    val idType: AppConst.User.IdType,

    @Column(nullable = false, length = 20)
    @Comment("ID 값 (주민등록번호, 사업자등록번호) -idType 컬럼 값에 따라 형식 변경")
    val idValue: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20)
    var role: AppConst.User.Role = AppConst.User.Role.USER,

    @Comment("최신 로그인 이력 IDX")
    val latestLoginHistoryIdx: Long? = null,

    @Column(nullable = false)
    @Comment("등록일시")
    val regDtm: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    @Comment("수정일시")
    val modDtm: LocalDateTime = LocalDateTime.now(),

    ) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(this.role.name))
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
}