package com.moin.api.domain.user.dto

import com.fin.best.bestfin.api.component.constants.AppConst
import com.moin.api.component.annotation.CheckEnum
import com.moin.api.component.validation.StringValidator
import com.moin.api.component.validation.ValidationUtil
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.slf4j.LoggerFactory

data class SignupRequestDTO(
    @field:Email
    @field:NotBlank
    val userId: String,

    @field:NotBlank
    @field:Size(min = 6)
    // TODO : 필요 시 Password 규칙 추가
    val password: String,

    @field:NotBlank
    val name: String,

    @field:CheckEnum(AppConst.User.IdType::class)
    val idType: String,

    @field:NotBlank
    val idValue: String,
) {

    private val logger = LoggerFactory.getLogger(ValidationUtil::class.java)

    fun validate(): AppConst.ResCode? {
        when (idType) {
            AppConst.User.IdType.REG_NO.code ->
                if (!StringValidator.isValidResidentRegistrationNumber(idValue)) {
                    logger.info("Validation Error : REG_NO => $idValue")
                    return AppConst.ResCode.BAD_REQUEST
                }
            AppConst.User.IdType.BUSINESS_NO.code ->
                if (!StringValidator.isValidBusinessRegistrationNumber(idValue)) {
                    logger.info("Validation Error : BUSINESS_NO => $idValue")
                    return AppConst.ResCode.BAD_REQUEST
                }
        }
        return null
    }
}