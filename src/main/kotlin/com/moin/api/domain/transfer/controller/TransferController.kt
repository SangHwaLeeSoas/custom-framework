package com.moin.api.domain.transfer.controller

import com.fin.best.bestfin.api.component.constants.AppConst
import com.moin.api.component.model.Response
import com.moin.api.component.util.ValidationUtil
import com.moin.api.domain.transfer.model.QuoteRequestDTO
import com.moin.api.domain.transfer.service.TransferService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transfer")
class TransferController(
    private val transferService: TransferService,
) {

    private val logger = LoggerFactory.getLogger(TransferController::class.java)


    @PostMapping("/quote")
    fun quote(
        @RequestBody params: QuoteRequestDTO
    ): Response {

        ValidationUtil.validateThrows(params)
        val result = transferService.makeQuote(params)

        return Response(AppConst.ResCode.OK, result)
    }


}