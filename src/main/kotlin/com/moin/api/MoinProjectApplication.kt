package com.moin.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MoinProjectApplication

fun main(args: Array<String>) {
	runApplication<MoinProjectApplication>(*args)
}
