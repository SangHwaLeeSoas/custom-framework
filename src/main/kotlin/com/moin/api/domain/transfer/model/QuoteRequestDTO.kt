package com.moin.api.domain.transfer.model

import com.fin.best.bestfin.api.component.constants.AppConst
import com.moin.api.component.annotation.CheckEnum
import jakarta.validation.constraints.Min

data class QuoteRequestDTO(

    @field:Min(1)
    val amount: Long,

    @field:CheckEnum(AppConst.Transfer.TargetCurrency::class)
    val targetCurrency: String
)