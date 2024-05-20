package com.moin.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class AuthConfig (
//    private val userRepository: UserRepository
) {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()


//    @Bean
//    fun userDetailsService() = UserDetailsService { userRepository.findByUserId(it) }
//
//
//    @Bean
//    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager =
//        configuration.authenticationManager
//
//
//    @Bean
//    fun authenticationProvider() = DaoAuthenticationProvider().apply {
//        setUserDetailsService(userDetailsService())
//        setPasswordEncoder(passwordEncoder())
//    }

}
