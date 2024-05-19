package com.moin.api.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fin.best.bestfin.api.component.constants.AppConst
import com.moin.api.component.model.Response
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component


@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {

        /* TODO: authException 종류에 따른 분기 */

        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpStatus.UNAUTHORIZED.value()

        val objectMapper = jacksonObjectMapper()
        val responseData = Response(AppConst.ResCode.UNAUTHORIZED).body

        objectMapper.writeValue(response.outputStream, responseData)
    }

//    private fun determineMessage(authException: AuthenticationException): String {
//        return when (authException) {
//            is BadCredentialsException -> "Invalid username or password"
//            is AccountExpiredException -> "Account expired"
//            is CredentialsExpiredException -> "Credentials expired"
//            is DisabledException -> "Account disabled"
//            is LockedException -> "Account locked"
//            else -> "Unauthorized access"
//        }
//    }
}