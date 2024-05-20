package com.moin.api.component.model

import com.fin.best.bestfin.api.component.constants.AppConst
import org.springframework.http.ResponseEntity

class Response : ResponseEntity<Any> {

    constructor(resCode: AppConst.ResCode) : super(ResponseData(resCode), resCode.httpStatus)
    constructor(resCode: AppConst.ResCode, data: Map<String,Any?>) : super(ResponseData(resCode, data), resCode.httpStatus)

//    fun isSuccess(): Boolean {
//        return (this.statusCode == AppConst.ResCode.OK.httpStatus)
//    }
//
//    fun getValues(): String? {
//        return ((this.body as ResponseData).values)?.toString()
//    }
}
