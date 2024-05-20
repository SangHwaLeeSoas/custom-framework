package com.moin.api.component.model

import java.util.*

data class AuthTokenDTO(
    val userId: String,
    val token: String,
    val expireDtm: Date
)