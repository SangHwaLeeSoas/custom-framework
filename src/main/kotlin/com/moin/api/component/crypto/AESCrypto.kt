package com.moin.api.component.crypto

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Component
class AESCrypto(
    @Value("\${crypto.aes.key}") private val SECRET_KEY: String,
    @Value("\${crypto.aes.iv}") private val IV: String
) {
    private val logger: Logger = LoggerFactory.getLogger(AESCrypto::class.java)

    private val ENCRYPT_ALGORITHM_AES = "AES/CBC/PKCS5Padding"
    private val ENCODING_CHARSET = StandardCharsets.UTF_8

    fun encrypt(str: String): String? {
        val c = Cipher.getInstance(ENCRYPT_ALGORITHM_AES)
        c.init(Cipher.ENCRYPT_MODE, this.generateDefaultKeySpec(), generateIVDefault())

        val encrypted = c.doFinal(str.toByteArray(ENCODING_CHARSET))
        return Base64.getEncoder().encodeToString(encrypted)
    }

    fun decrypt(str: String): String {
        val c = Cipher.getInstance(ENCRYPT_ALGORITHM_AES)
        c.init(Cipher.DECRYPT_MODE, this.generateDefaultKeySpec(), this.generateIVDefault())

        val byteStr: ByteArray = Base64.getDecoder().decode(str.toByteArray())
        return String(c.doFinal(byteStr), Charsets.UTF_8)
    }

    private fun generateIV(key: String): IvParameterSpec {
        val iv: ByteArray = key.substring(0, 16).toByteArray()
        return IvParameterSpec(iv)
    }

    private fun generateIVDefault(): IvParameterSpec {
        val defaultKey = IV
        return generateIV(defaultKey)
    }

    private fun generateKeySpec(key: String): SecretKeySpec {
        val keyBytes = ByteArray(16)
        val b = key.toByteArray(ENCODING_CHARSET)

        var len = b.size
        if (len > keyBytes.size) {
            len = keyBytes.size
        }

        System.arraycopy(b, 0, keyBytes, 0, len)
        return SecretKeySpec(keyBytes, "AES")
    }

    private fun generateDefaultKeySpec(): SecretKeySpec {
        val defaultKey = SECRET_KEY
        return generateKeySpec(defaultKey)
    }
}