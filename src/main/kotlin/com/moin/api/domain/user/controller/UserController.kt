package com.moin.api.domain.user.controller

import com.fin.best.bestfin.api.component.constants.AppConst
import com.moin.api.component.model.Response
import com.moin.api.component.validation.ValidationUtil
import com.moin.api.domain.user.dto.SignupRequestDTO
import com.moin.api.domain.user.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
) {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @PostMapping("/signup")
    fun signup(
        @RequestBody params: SignupRequestDTO
    ): Response {
        logger.info("CALLED SIGNUP : $params")

        ValidationUtil.validateThrows(params)
        userService.makeUser(params)

        return Response(AppConst.ResCode.OK)
    }
}