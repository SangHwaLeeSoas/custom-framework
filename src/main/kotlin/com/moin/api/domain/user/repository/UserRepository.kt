package com.moin.api.domain.user.repository

import com.moin.api.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

    fun findByUserId(userid: String): User?
}