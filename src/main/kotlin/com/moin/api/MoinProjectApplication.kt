package com.moin.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class MoinProjectApplication

fun main(args: Array<String>) {
	runApplication<MoinProjectApplication>(*args)
}
