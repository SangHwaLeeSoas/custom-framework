package com.moin.api.domain.user.controller

import com.fin.best.bestfin.api.component.constants.AppConst
import com.moin.api.component.model.Response
import com.moin.api.component.validation.ValidationUtil
import com.moin.api.domain.user.dto.SignupRequestDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {


    @PostMapping("/signup")
    fun signup(
        @RequestBody params: SignupRequestDTO
    ): Response {
        println("########")
        println(params)
        println(params)

        ValidationUtil.validateThrows(params)

        return Response(AppConst.ResCode.OK)
    }
}