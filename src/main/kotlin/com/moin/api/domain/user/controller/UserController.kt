package com.moin.api.domain.user.controller

import com.fin.best.bestfin.api.component.constants.AppConst
import com.moin.api.component.model.Response
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {


    @PostMapping("/test")
    fun test(): Response {
        return Response(AppConst.ResCode.OK)
    }
}