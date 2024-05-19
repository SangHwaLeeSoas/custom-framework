package com.moin.api.domain.user.repository

import com.moin.api.domain.user.entity.LoginHistory
import org.springframework.data.jpa.repository.JpaRepository

interface LoginHistoryRepository: JpaRepository<LoginHistory, Long> {

}