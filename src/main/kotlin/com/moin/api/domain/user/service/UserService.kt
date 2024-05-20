package com.moin.api.domain.user.service

import com.fin.best.bestfin.api.component.constants.AppConst
import com.moin.api.component.exception.CommonException
import com.moin.api.component.model.AuthTokenDTO
import com.moin.api.component.util.DateUtil
import com.moin.api.domain.user.entity.LoginHistory
import com.moin.api.domain.user.entity.User
import com.moin.api.domain.user.model.SignupRequestDTO
import com.moin.api.domain.user.repository.LoginHistoryRepository
import com.moin.api.domain.user.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val loginHistoryRepository: LoginHistoryRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
) {


    private val logger = LoggerFactory.getLogger(UserService::class.java)


    /* 회원 정보 생성 */
    @Transactional
    @Throws(CommonException::class)
    fun makeUser(signupRequestDTO: SignupRequestDTO) {

        checkDuplicatedUser(signupRequestDTO.userId)

        val encodedPassword = passwordEncoder.encode(signupRequestDTO.password)
        val encodedIdValue = passwordEncoder.encode(signupRequestDTO.password)
        val user = User(
            userId = signupRequestDTO.userId,
            userPassword = encodedPassword,
            name = signupRequestDTO.name,
            idType = signupRequestDTO.idType,
            idValue = encodedIdValue,
            role = AppConst.User.Role.USER.code
        )
        logger.info("MAKE_USER : $user")
        userRepository.save(user)
    }


    /* 로그인 정보 생성 */
    @Transactional
    @Throws(CommonException::class)
    fun makeLoginHistory(authTokenDTO: AuthTokenDTO) {

        val user = userRepository.findByUserId(authTokenDTO.userId)
            ?: throw CommonException(AppConst.ResCode.NOT_FOUND_USER)

        /* TODO: 회원 상태 검증 */

        val loginHistory = LoginHistory(
            userIdx = user.userIdx,
            authTokenCode = authTokenDTO.token,
            authTokeExpireDtm = DateUtil.dateToLocalDateTime(authTokenDTO.expireDtm),
        )
        loginHistoryRepository.save(loginHistory)
    }


    /* userId 중복 체크 */
    @Throws(CommonException::class)
    private fun checkDuplicatedUser(userId: String) {
        val user = userRepository.findByUserId(userId)
        logger.info("USER : $user")
        if (user != null)
            throw CommonException(AppConst.ResCode.DUPLICATED_USER_ID)
    }


    fun getUserInfo(userId: String) {
        logger.info("getUserInfo")
    }


}