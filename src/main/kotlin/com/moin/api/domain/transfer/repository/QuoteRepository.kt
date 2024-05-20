package com.moin.api.domain.transfer.repository

import com.moin.api.domain.transfer.entity.Quote
import com.moin.api.domain.transfer.model.DailyTransferSummaryProjection
import com.moin.api.domain.transfer.model.QuoteListProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface QuoteRepository: JpaRepository<Quote, Long> {


    @Query(value = """
        SELECT 
            q.source_amount AS sourceAmount,
            q.fiexed_fee AS fee,
            q.target_currency AS targetCurrency,
            q.exchange_rate AS exchangeRate,
            q.target_amount AS targetAmount,
            q.requested_date AS requestedDate
        FROM quote q
        WHERE q.user_idx = :userIdx
    """, nativeQuery = true)
    fun findQuoteList(@Param("userIdx") userIdx: Long): List<QuoteListProjection>


    @Query(value = """
        SELECT 
            COALESCE(SUM(q.source_amount), 0) AS totalSourceAmount, 
            COUNT(*) AS totalTransferCount
        FROM quote q 
        WHERE q.status_code = :statusCode 
        AND CAST(q.requested_date AS DATE) = :requestedDate 
        AND q.user_idx = :userIdx
    """, nativeQuery = true)
    fun findDailyTransferSummary(
        @Param("statusCode") statusCode: String,
        @Param("requestedDate") requestedDate: LocalDate,
        @Param("userIdx") userIdx: Long
    ): DailyTransferSummaryProjection


}