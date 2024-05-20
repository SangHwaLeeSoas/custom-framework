package com.moin.api.component.util

import com.fin.best.bestfin.api.component.constants.AppConst
import com.moin.api.component.exception.CommonException
import jakarta.validation.ConstraintViolation
import jakarta.validation.Validation
import jakarta.validation.Validator
import org.slf4j.LoggerFactory
import kotlin.reflect.full.declaredFunctions

/**
 *  @name         : ValidationUtil
 *  @author       : SHL
 *  @date         : 2022-11-28
 *  @description  : javax validator, spring validator 사용 Custom Validator
 **/
object ValidationUtil {

    private val logger = LoggerFactory.getLogger(ValidationUtil::class.java)
    private val validator: Validator = Validation.buildDefaultValidatorFactory().validator


    /**
     *  @name         : validateThrows
     *  @author       : SHL
     *  @date         : 2022-11-28
     *  @description  : 유효성 검사 => 실패 시 CommonException 발생
     *                  1. 여러 데이터 실패 시 첫번 째 실패 응답 코드로 예외 생성
     *                  2. 데이터 선언부에 ResCode를 message로 설정되어있지 않으면 Default : INVALID_DATA_TYPE
     *  @param        : obj : T (Generic type)
     *  @throws       : CommonException
     **/
    @Throws(CommonException::class)
    fun <T> validateThrows(obj: T) {

        /* Default validator */
        val defaultInvalid: AppConst.ResCode? = validateDefault(obj)

        if (defaultInvalid != null)
            throw CommonException(defaultInvalid)

        /* Local validator */
        val localInvalid: AppConst.ResCode? = validateLocal(obj)
        if (localInvalid != null)
            throw CommonException(localInvalid)

    }


    /**
     *  @name         : validateDefault
     *  @author       : SHL
     *  @date         : 2022-11-28
     *  @description  : Javax를 포함한 Custom Annotation으로 정의된 기본 Validate 검사
     *  @param        : obj : T (Generic type)
     *  @return       : AppConst.ResCode? (유효성 검사 통과 시 null return)
     **/
    private fun <T> validateDefault(obj: T): AppConst.ResCode? {
        val violations: Set<ConstraintViolation<T>> = validator.validate(obj)
        if (violations.isNotEmpty()) {
            logger.info("Validation Error : $violations")
            val resCode: AppConst.ResCode = AppConst.ResCode.BAD_REQUEST
            try {
//                resCode = AppConst.ResCode.valueOf(violations.iterator().next().messageTemplate)
            } catch (il: IllegalArgumentException) {
            } finally {
                return resCode
            }
        }
        return null
    }


    /**
     *  @name         : validateLocal
     *  @author       : SHL
     *  @date         : 2022-11-28
     *  @description  : Parameter class에 선언된 validate 함수를 찾아 실행
     *  @param        : obj : T (Generic type)
     *  @return       : AppConst.ResCode? (유효성 검사 통과 시 null return)
     **/
    private fun <T> validateLocal(obj: T): AppConst.ResCode? {
        val localValidate = obj!!::class.declaredFunctions.filter { it.name == "validate" }
        if (localValidate.isNotEmpty()) {
            val localInvalid = localValidate[0].call(obj) as AppConst.ResCode?
            if (localInvalid != null)
                return localInvalid
        }
        return null
    }


}