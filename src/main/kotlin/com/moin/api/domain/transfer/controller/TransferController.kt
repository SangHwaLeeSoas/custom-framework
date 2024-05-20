package com.moin.api.domain.transfer.controller

import com.fin.best.bestfin.api.component.constants.AppConst
import com.moin.api.component.model.Response
import com.moin.api.component.util.ValidationUtil
import com.moin.api.domain.transfer.model.QuoteRequestDTO
import com.moin.api.domain.transfer.model.TransferRequestDTO
import com.moin.api.domain.transfer.service.TransferService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

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


    @PostMapping("/request")
    fun request(
        @RequestBody params: TransferRequestDTO
    ): Response {

        ValidationUtil.validateThrows(params)
        transferService.requestTransfer(params)

        return Response(AppConst.ResCode.OK)
    }


    @GetMapping("/list")
    fun list(): Response {

        val result = transferService.getQuoteList()

        return Response(AppConst.ResCode.OK, result)
    }

}