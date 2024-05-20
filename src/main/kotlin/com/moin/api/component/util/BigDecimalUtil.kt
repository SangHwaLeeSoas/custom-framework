package com.moin.api.component.util

import java.math.BigDecimal

object BigDecimalUtil {


    /* 0인지 체크 */
    fun isZero(value: BigDecimal): Boolean {
        return value.compareTo(BigDecimal.ZERO) == 0
    }
}