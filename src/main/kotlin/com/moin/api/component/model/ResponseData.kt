package com.moin.api.component.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fin.best.bestfin.api.component.constants.AppConst

class ResponseData {
    var resultCode: Int = 200
    var resultMsg: String = AppConst.ResCode.OK.httpStatus.name
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var values: Any? = null

    constructor(resCode: AppConst.ResCode) {
        this.resultCode = resCode.httpStatus.value()
        this.resultMsg = resCode.detail
    }

    constructor(resCode: AppConst.ResCode, responseData: Any?) {
        this.resultCode = resCode.httpStatus.value()
        this.resultMsg = resCode.detail
        this.values = responseData
    }


}