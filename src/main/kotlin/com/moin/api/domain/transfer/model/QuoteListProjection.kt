package com.moin.api.domain.transfer.model

import java.math.BigDecimal
import java.time.LocalDateTime

interface QuoteListProjection {

    val sourceAmount: Long
    val fee: Long
    val targetCurrency: String
    val exchangeRate: BigDecimal
    val targetAmount: BigDecimal
    val requestedDate: LocalDateTime
}

interface DailyTransferSummaryProjection {
    val totalSourceAmount: Long
    val totalTransferCount: Int
}