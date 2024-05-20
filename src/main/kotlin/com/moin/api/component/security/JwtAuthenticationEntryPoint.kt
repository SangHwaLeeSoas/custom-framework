package com.moin.api.component.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fin.best.bestfin.api.component.constants.AppConst
import com.moin.api.component.model.Response
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component


@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

    private val logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint::class.java)

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {

        /* TODO: 필요시 ErrorCode 세분화 */
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpStatus.UNAUTHORIZED.value()

        val objectMapper = jacksonObjectMapper()
        val responseData = Response(AppConst.ResCode.UNAUTHORIZED).body

        objectMapper.writeValue(response.outputStream, responseData)

        logger.info("Unauthorized access")
    }

}