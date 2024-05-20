package com.moin.api.component.util

import com.moin.api.domain.user.entity.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserDetailsUtil {

    fun getUserDetails(): User {
        val authentication = SecurityContextHolder.getContext().authentication
        return authentication.principal as User
    }
}