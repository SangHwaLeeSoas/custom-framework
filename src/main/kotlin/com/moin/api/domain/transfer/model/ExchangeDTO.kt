package com.moin.api.domain.transfer.model

import java.math.BigDecimal

data class ExchangeDTO(
    val basePrice: BigDecimal,
    val currencyUnit: Int
)
