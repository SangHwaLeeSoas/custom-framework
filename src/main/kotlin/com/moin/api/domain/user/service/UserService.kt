package com.moin.api.domain.user.service

import com.moin.api.domain.user.repository.LoginHistoryRepository
import com.moin.api.domain.user.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val loginHistoryRepository: LoginHistoryRepository,
) {

    private val logger = LoggerFactory.getLogger(UserService::class.java)


    fun getUserInfo(userId: String) {
        logger.info("getUserInfo")
    }


    @Transactional
    fun makeLoginHistory(userId: String) {
        logger.info("makeUser")
    }
}