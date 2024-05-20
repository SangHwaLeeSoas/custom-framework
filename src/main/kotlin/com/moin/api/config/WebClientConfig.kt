package com.moin.api.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(
    @Value("\${dunamnu.base-url}") private val HANA_BASE_URL: String,
) {

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .baseUrl(HANA_BASE_URL)
            .build()
    }
}