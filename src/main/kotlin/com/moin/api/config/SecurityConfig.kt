package com.moin.api.config

import com.moin.api.component.security.CustomAuthenticationSuccessHandler
import com.moin.api.component.security.JwtAuthenticationEntryPoint
import com.moin.api.component.security.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val authenticationProvider: AuthenticationProvider,
    private val customSccessHandler: CustomAuthenticationSuccessHandler,
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .headers { headers ->
                headers.frameOptions { frameOptions ->
                    frameOptions.disable()  // X-Frame-Options 헤더 비활성화
                }
            }
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/user/signup").permitAll()
                    .requestMatchers("/h2-console/**").permitAll()  /* H2 CONSOLE */
                    .anyRequest().authenticated()
            }
            .exceptionHandling { exceptions -> exceptions.authenticationEntryPoint(jwtAuthenticationEntryPoint) }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .formLogin {
                it.loginProcessingUrl("/user/login")
                    .usernameParameter("userId")
                    .passwordParameter("password")
                    .successHandler(customSccessHandler)
            }
            .build()


}