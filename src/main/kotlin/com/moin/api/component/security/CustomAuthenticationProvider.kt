package com.moin.api.component.security

import com.moin.api.domain.user.entity.User
import com.moin.api.domain.user.service.CustomUserDetailService
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component


@Component
class CustomAuthenticationProvider(
    private val passwordEncoder: BCryptPasswordEncoder,
    private val userDetailsService: CustomUserDetailService,
) : AuthenticationProvider {

    private val logger = LoggerFactory.getLogger(CustomAuthenticationProvider::class.java)

    override fun authenticate(authentication: Authentication?): Authentication {
        val token = authentication as UsernamePasswordAuthenticationToken

        val userId = token.name
        val password = token.credentials as String

        val customUserDetails: User = userDetailsService.loadUserByUsername(userId)

        if (!(customUserDetails.username == userId && passwordEncoder.matches(
                password,
                customUserDetails.password
            ))
        ) {
            throw BadCredentialsException("Mismatch userId or password : $customUserDetails.userId")
        }
        return UsernamePasswordAuthenticationToken(customUserDetails, password, customUserDetails.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}