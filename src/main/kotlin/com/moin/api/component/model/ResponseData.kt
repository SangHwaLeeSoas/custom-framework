package com.moin.api.component.model

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fin.best.bestfin.api.component.constants.AppConst

class ResponseData {
    var resultCode: Int = 200
    var resultMsg: String = AppConst.ResCode.OK.httpStatus.name
    private val additionalProperties: MutableMap<String, Any?> = mutableMapOf()

    constructor(resCode: AppConst.ResCode) {
        this.resultCode = resCode.httpStatus.value()
        this.resultMsg = resCode.detail
    }

    constructor(resCode: AppConst.ResCode, data: Map<String,Any?>) {
        this.resultCode = resCode.httpStatus.value()
        this.resultMsg = resCode.detail
        this.addProperties(data)
    }

    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any?> = additionalProperties

    private fun addProperties(map: Map<String, Any?>) {
        additionalProperties.putAll(map)
    }

}