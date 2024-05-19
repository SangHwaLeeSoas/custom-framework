package com.moin.api.component.annotation

import com.moin.api.component.validation.EnumValidator
import jakarta.validation.Constraint
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = [EnumValidator::class])
/**
 ** Custom @Enum annotation from Constraint
 *  @author       : SHL
 *  @date         : 2023-01-10
 **/
annotation class CheckEnum(
    val enumClass: KClass<out Any>,
    val message: String = "INVALID_ENUM_TYPE",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Any>> = [],
    val isIgnoreCase: Boolean = false,
    val isArrayType: Boolean = false

)