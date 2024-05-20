package com.moin.api.config

import com.moin.api.component.security.CustomAuthenticationFilter
import com.moin.api.component.security.JwtAuthenticationEntryPoint
import com.moin.api.component.security.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
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
    private val authenticationConfiguration: AuthenticationConfiguration,
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
            .addFilterBefore(customAuthenticationFilter(authenticationManager(authenticationConfiguration)), UsernamePasswordAuthenticationFilter::class.java)
            .formLogin {
                it.loginProcessingUrl("/user/login")
                    .usernameParameter("userId")
                    .passwordParameter("password")
            }
            .build()



    // 인증 제공자로 사용자의 이름과 비밀번호가 요구된다.
//    @Bean
//    fun customAuthenticationProvider(): CustomAuthenticationProvider {
//        return CustomAuthenticationProvider(passwordEncoder(), userDetailService)
//    }


    // authenticate의 인증 메서드를 제공하는 매니저로 Provider의 인터페이스를 의미
    @Bean
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager =
        configuration.authenticationManager

    // 커스텀을 수행한 인증 필터로 접근 URL, 데이터 전달방식 등 인증과정 및 인증 후 처리에 대한 설정 구성하는 메소드
    @Bean
    fun customAuthenticationFilter(authenticationManager: AuthenticationManager): CustomAuthenticationFilter {
        return CustomAuthenticationFilter().apply {
            setFilterProcessesUrl("/user/login")
        }
    }

}