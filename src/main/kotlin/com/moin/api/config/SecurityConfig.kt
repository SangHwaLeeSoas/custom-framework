package com.moin.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val authenticationProvider: AuthenticationProvider
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? =
        http
            .headers { headers ->
                headers.frameOptions { frameOptions ->
                    frameOptions.disable()  // X-Frame-Options 헤더 비활성화
                }
            }
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/h2-console/**").permitAll()  /* H2 CONSOLE*/
                    .anyRequest().authenticated()
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .logout {
                it
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                    .invalidateHttpSession(true)
            }
            .build()

}