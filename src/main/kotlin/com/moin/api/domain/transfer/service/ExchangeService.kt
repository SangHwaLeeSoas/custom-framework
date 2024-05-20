package com.moin.api.domain.transfer.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fin.best.bestfin.api.component.constants.AppConst
import com.moin.api.component.exception.CommonException
import com.moin.api.domain.transfer.model.ExchangeDTO
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@Service
class ExchangeService(
    private val webClient: WebClient,
    @Value("\${dunamnu.exchange-rate.uri}") private val EXCHANGE_URI: String,
) {

    private val logger = LoggerFactory.getLogger(ExchangeService::class.java)


    /* TODO : API 호출 공통 Util로 분리
    *  추후 캐싱 작업 필요요=> 공시 변경 정책에 따라 매번 API 호출할 필요는 없어보임.
    *  (ex. 주말은 금요일 마지막 공시회차 데이터가 계속 유지됨을 확인함)
    * */
    fun getExchangeInfo(currencyCode: String): ExchangeDTO? {
        logger.info("exchange !!!")
        return try {
            val responseString = webClient.get()
                .uri(EXCHANGE_URI)
                .retrieve()
                .onStatus({ status -> status.is4xxClientError || status.is5xxServerError }) { response ->
                    response.bodyToMono<String>().flatMap { body ->
                        Mono.error(RuntimeException("Error response: ${response.statusCode()} - $body"))
                    }
                }
                .bodyToMono<String>()
                .block()

            logger.info("responseString: $responseString")

            val objectMapper = jacksonObjectMapper()
            val typeRef = object : TypeReference<List<Map<String, Any>>>() {}
            val result: List<Map<String, Any>> = objectMapper.readValue(responseString, typeRef)

            result
                .filter { it["code"] == currencyCode }
                .map {
                    ExchangeDTO(
                        basePrice = it["basePrice"].toString().toBigDecimal(),
                        currencyUnit = it["currencyUnit"] as Int
                    )
                }
                .firstOrNull()
        } catch (e: WebClientResponseException) {
            logger.error("WebClientResponseException: ${e.statusCode} - ${e.responseBodyAsString}")
            throw CommonException(AppConst.ResCode.EXCHANGE_SERVER_ERROR)
        } catch (e: Exception) {
            logger.error("Exception: ${e.message}")
            throw CommonException(AppConst.ResCode.INTERNAL_SERVER_ERROR)
        }
    }
}