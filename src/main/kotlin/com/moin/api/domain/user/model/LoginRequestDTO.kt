package com.moin.api.domain.user.model

data class LoginRequestDTO(
    val userId: String,
    val password: String
)