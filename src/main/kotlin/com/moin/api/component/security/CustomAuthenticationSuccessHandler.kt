package com.moin.api.component.security

import com.moin.api.domain.user.service.UserService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component


@Component
class CustomAuthenticationSuccessHandler(
    private val jwtService: JwtService,
    private val userService: UserService,
) : AuthenticationSuccessHandler {

    private val logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler::class.java)

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val token = jwtService.generateToken(authentication)
        logger.info("token: $token")
        logger.info("authentication: $authentication")

        response.contentType = "application/json;charset=UTF-8"
        response.writer.print("{\"token\": \"$token\"}")
        response.writer.flush()
    }
}