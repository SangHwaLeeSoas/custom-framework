package com.moin.api.component.util

import java.util.*

object CurrencyUtil {
    fun getDefaultFractionDigits(currencyCode: String): Int {
        val currency = Currency.getInstance(currencyCode)
        return currency.defaultFractionDigits
    }


}