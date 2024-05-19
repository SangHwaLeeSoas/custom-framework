package com.moin.api.component.validation

import com.moin.api.component.annotation.CheckEnum
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class EnumValidator: ConstraintValidator<CheckEnum, String> {


    private lateinit var annotation: CheckEnum


    override fun initialize(constraintAnnotation: CheckEnum) {
        this.annotation = constraintAnnotation
    }


    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {

        val enumValues: Array<out Any> = annotation.enumClass.java.enumConstants
        if (enumValues.isNotEmpty()) {

            val enumValueArr = mutableListOf<String>()
            for (enumValue in enumValues) {
                if (value.equals(enumValue.toString()) || (annotation.isIgnoreCase && value.equals(enumValue.toString(), true))) {
                    return true
                }
                enumValueArr.add(enumValue.toString())
            }

            if (annotation.isArrayType) {
                value?.split(",")?.forEach {
                    if (enumValueArr.contains(it) || (annotation.isIgnoreCase && enumValueArr.contains(it.uppercase()))) {
                        return true
                    }
                }
            }

        }
        return false
    }


}