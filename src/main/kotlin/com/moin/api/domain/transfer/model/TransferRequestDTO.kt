package com.moin.api.domain.transfer.model

import jakarta.validation.constraints.Min

data class TransferRequestDTO(

    @field:Min(1)
    val quoteId: Long,
)