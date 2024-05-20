package com.moin.api.component.crypto

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = false)
class StringCryptoConverter(
    private val AESCrypto: AESCrypto,
) : AttributeConverter<String, String> {


    override fun convertToDatabaseColumn(attribute: String?): String? {
        return if (attribute == null) {
            null
        } else {
            AESCrypto.encrypt(attribute)
        }
    }


    override fun convertToEntityAttribute(dbData: String?): String? {
        return if (dbData == null) {
            null
        } else {
            try {
                AESCrypto.decrypt(dbData)
            } catch (e: Exception) {
                dbData
            }
        }
    }
}
