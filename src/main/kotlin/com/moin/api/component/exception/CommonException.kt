package com.moin.api.component.exception

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fin.best.bestfin.api.component.constants.AppConst

@JsonIgnoreProperties(value = ["cause", "stackTrace", "suppressed"])
open class CommonException(resCode: AppConst.ResCode, result: Any? = null) : RuntimeException() {
    var resCode: AppConst.ResCode = resCode
    var result: Any? = result
}
