package com.moin.api.domain.transfer.repository

import com.moin.api.domain.transfer.entity.Quote
import org.springframework.data.jpa.repository.JpaRepository

interface QuoteRepository: JpaRepository<Quote, Long> {

}