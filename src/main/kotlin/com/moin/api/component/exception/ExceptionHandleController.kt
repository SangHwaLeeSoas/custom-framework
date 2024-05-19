package com.moin.api.component.exception

import com.moin.api.component.model.Response
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody


@ControllerAdvice
class ExceptionHandleController {

    @ExceptionHandler(value = [CommonException::class])
    @ResponseBody
    fun commonException(commonException: CommonException, request: HttpServletRequest): Response {
        return Response(commonException.resCode,  commonException.result)
    }

}