package com.moin.api.component.exception

import com.moin.api.component.model.Response
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody


@ControllerAdvice
class ExceptionHandleController {

    /**
     *  @Name        : commonException
     *  @Author      : SHL
     *  @Date        : 2022-10-11
     *  @Description : 공통 사용 Custom Exception
     *                Controller에서 사용 중인 ResponseEntity<ResponseDataValue> 형태와 동일한 Data type
    **/
    @ExceptionHandler(value = [CommonException::class])
    @ResponseBody
    fun commonException(commonException: CommonException, request: HttpServletRequest): Response {
        return Response(commonException.resCode,  commonException.result)
    }

}