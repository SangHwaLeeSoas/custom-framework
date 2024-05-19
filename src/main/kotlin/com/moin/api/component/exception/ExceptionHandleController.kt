package com.moin.api.component.exception

import com.fin.best.bestfin.api.component.constants.AppConst
import com.moin.api.component.model.Response
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.ServletRequestBindingException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.multipart.MultipartException
import org.springframework.web.multipart.support.MissingServletRequestPartException


@ControllerAdvice
class ExceptionHandleController {


    /* Common Exception*/
    @ExceptionHandler(value = [CommonException::class])
    @ResponseBody
    fun commonException(commonException: CommonException, request: HttpServletRequest): Response {
        return Response(commonException.resCode,  commonException.result)
    }


    /* BAD REQUEST */
    @ExceptionHandler(value = [
        MultipartException::class,
        MissingServletRequestParameterException::class,
        MissingServletRequestPartException::class,
        MethodArgumentTypeMismatchException::class,
        ServletRequestBindingException::class,
        HttpRequestMethodNotSupportedException::class,
        HttpMessageNotReadableException::class,
        HttpMediaTypeNotSupportedException::class
    ])
    @ResponseBody
    fun badRequest(exception: Exception, request: HttpServletRequest): Any {
        return Response(AppConst.ResCode.BAD_REQUEST)
    }

    /* Exception */
    @ExceptionHandler(value = [Exception::class])
    @ResponseBody
    fun unhandledException(commonException: Exception, request: HttpServletRequest): Response {
        return Response(AppConst.ResCode.INTERNAL_SERVER_ERROR)
    }

}