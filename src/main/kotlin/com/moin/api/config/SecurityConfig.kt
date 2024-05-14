package com.moin.api.config

import com.fin.best.bestfin.api.config.oauth2.RestAuthenticationEntryPoint
import com.fin.best.bestfin.api.config.oauth2.client.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@Order(SecurityProperties.IGNORED_ORDER)
class SecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private lateinit var oAuth2UserService: OAuth2UserService

    @Autowired
    private lateinit var oAuth2AuthenticationSuccessHandler: OAuth2AuthenticationSuccessHandler

    @Autowired
    private lateinit var oAuth2AuthenticationFailureHandler: OAuth2AuthenticationFailureHandler

    @Bean
    fun oauth2AuthorizationRequestRepository(): OAuth2AuthorizationRequestRepository {
        return OAuth2AuthorizationRequestRepository()
    }

    @Bean(name = ["userPasswordEncoder"])
    fun userPasswordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        return OAuth2UserDetailsService()
    }

    @Bean(name = ["authenticationManager"])
    @Lazy
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService()).passwordEncoder(userPasswordEncoder())
    }

//    @Bean
//    fun securityAuthenticationFilter(): SecurityAuthenticationFilter? {
//        return SecurityAuthenticationFilter()
//    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .cors().and()
            .csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/**").permitAll()
            .antMatchers("/auth/**").authenticated()
            .antMatchers("/**").authenticated()
            .anyRequest().permitAll()
            .and()
            .formLogin().disable()

        // my oauth2 authorization server
        http.antMatcher("/oauth/**")
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .formLogin().disable()
                .exceptionHandling()
                .authenticationEntryPoint(RestAuthenticationEntryPoint())
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()

        // social oauth2 login
        http.antMatcher("/oauth2/**")
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .httpBasic()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(RestAuthenticationEntryPoint())
                .and()
                .authorizeRequests()

                .antMatchers("/auth/**", "/oauth2/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth2/authorize")
                .authorizationRequestRepository(oauth2AuthorizationRequestRepository())
                .and()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/*")
                .and()
                .userInfoEndpoint()
                .userService(oAuth2UserService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler)
    }

//    @Profile("!product")
//    @Throws(Exception::class)
//    override fun configure(web: WebSecurity) {
//        web.ignoring().antMatchers("/v2/api-docs",
//            "/swagger-resources/**",
//            "/swagger-ui.html",
//            "/webjars/**",
//            "/configuration/ui",
//            "/swagger/**")
//        super.configure(web)
//    }
}
