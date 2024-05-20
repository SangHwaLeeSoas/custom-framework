package com.moin.api.domain.user.dto

data class LoginRequestDTO(
    val userId: String,
    val password: String
)