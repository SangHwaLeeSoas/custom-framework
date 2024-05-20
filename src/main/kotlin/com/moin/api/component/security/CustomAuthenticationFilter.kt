package com.moin.api.component.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fin.best.bestfin.api.component.constants.AppConst
import com.moin.api.component.model.Response
import com.moin.api.domain.user.model.LoginRequestDTO
import com.moin.api.domain.user.service.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationFilter (
    private val jwtService: JwtService,
    private val userService: UserService
        ) : UsernamePasswordAuthenticationFilter() {

    @Autowired
    override fun setAuthenticationManager(authenticationManager: AuthenticationManager) {
        super.setAuthenticationManager(authenticationManager)
    }


    private val logger = LoggerFactory.getLogger(CustomAuthenticationFilter::class.java)


    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Authentication {
        logger.info("FILTER => attemptAuthentication !!!")
        val loginRequest = jacksonObjectMapper().readValue(request.inputStream, LoginRequestDTO::class.java)
        val authRequest = UsernamePasswordAuthenticationToken(loginRequest.userId, loginRequest.password)
        return authenticationManager.authenticate(authRequest)
    }


    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        logger.info("FILTER => successfulAuthentication !!!")
        logger.info(authResult)
        val tokenDTO = jwtService.generateToken(authResult)

        userService.makeLoginHistory(tokenDTO)

        val responseData = Response(AppConst.ResCode.OK, mapOf("token" to tokenDTO.token)).body
        writeResponse(response, HttpStatus.OK, responseData)
        logger.info("unsuccessfulAuthentication : $responseData")
    }


    override fun unsuccessfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        failed: AuthenticationException
    ) {
        val resCode = determineMessage(failed)
        val responseData = Response(resCode).body
        writeResponse(response, HttpStatus.UNAUTHORIZED, responseData)
        logger.info("unsuccessfulAuthentication : $responseData")
    }

    /* 공통 응답 설정 메소드 */
    private fun writeResponse(response: HttpServletResponse, status: HttpStatus, responseData: Any?) {
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = status.value()
        jacksonObjectMapper().writeValue(response.outputStream, responseData)
        logger.info("Response: $responseData")
    }

    /* AuthenticationProvider에서 발생한 AuthenticationException를 ResCode로 리턴 */
    private fun determineMessage(authException: AuthenticationException): AppConst.ResCode {
        return when (authException) {
            is UsernameNotFoundException -> AppConst.ResCode.NOT_FOUND_USER
            is BadCredentialsException -> AppConst.ResCode.BAD_CREDENTIALS
            else -> AppConst.ResCode.UNAUTHORIZED
        }
    }
}