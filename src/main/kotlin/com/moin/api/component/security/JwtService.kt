package com.moin.api.component.security

import com.moin.api.component.model.AuthTokenDTO
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*

@Service
class JwtService (
    @Value("\${jwt.secret}") private val SECRET_KEY: String,
    @Value("\${jwt.expiration}") private val EXPIRATION_TIME: Long
){

    fun extractUsername(token: String): String = extractClaim(token, Claims::getSubject)

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

//    fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String = Jwts.builder()
//        .setClaims(extraClaims)
//        .setSubject(userDetails.username)
//        .setIssuedAt(Date(System.currentTimeMillis()))
//        .setExpiration(Date(System.currentTimeMillis() + expirationTime))
//        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//        .compact()

    fun generateToken(authentication: Authentication): AuthTokenDTO {
        val now = Date()
        val expireDtm = Date(now.time + EXPIRATION_TIME)

        val token = Jwts.builder()
            .setSubject(authentication.name)
            .setIssuedAt(now)
            .setExpiration(expireDtm)
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()

        return AuthTokenDTO(authentication.name, token, expireDtm)
    }

    private fun extractAllClaims(token: String): Claims = Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .body

//    fun generateToken(userDetails: UserDetails): String = generateToken(HashMap(), userDetails)

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean = extractExpiration(token).before(Date())

    private fun extractExpiration(token: String): Date = extractClaim(token, Claims::getExpiration)

    private fun getSignInKey(): Key {
        val keyBytes = Decoders.BASE64.decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}