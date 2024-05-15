package com.moin.api.component.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fin.best.bestfin.api.component.constants.AppConst
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class Response : ResponseEntity<Any> {

    constructor(resCode: AppConst.ResCode) : super(ResponseData(resCode), resCode.httpStatus)
    constructor(resCode: AppConst.ResCode, responseData: Any?) : super(ResponseData(resCode, responseData), resCode.httpStatus)

//    fun isSuccess(): Boolean {
//        return (this.statusCode == AppConst.ResCode.OK.httpStatus)
//    }
//
//    fun getValues(): String? {
//        return ((this.body as ResponseData).values)?.toString()
//    }
}

class ResponseData {
//    var resultCode: Int = AppConst.Response.DefaultEmptyCode
    var resultCode: Int = 200
    var resultMsg: String = AppConst.ResCode.OK.httpStatus.name

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var values: Any? = null

    constructor(resCode: AppConst.ResCode) {
        this.resultCode = resCode.httpStatus.value()
        this.resultMsg = resCode.name
    }

    constructor(resCode: AppConst.ResCode, responseData: Any?) {
        this.resultCode = resCode.httpStatus.value()
        this.resultMsg = resCode.name
        this.values = responseData
    }
}
