package com.moin.api.domain.user.service

import com.moin.api.domain.user.entity.User
import com.moin.api.domain.user.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    private val userRepository: UserRepository,
) : UserDetailsService {

    private val logger = LoggerFactory.getLogger(CustomUserDetailService::class.java)

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(userId: String): User {
        return userRepository.findByUserId(userId)
            ?: throw UsernameNotFoundException("Not found user : $userId")
    }
}