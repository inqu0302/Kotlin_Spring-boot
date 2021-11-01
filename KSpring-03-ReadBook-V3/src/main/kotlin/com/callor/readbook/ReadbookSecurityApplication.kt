package com.callor.readbook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReadbookSecurityApplication

fun main(args: Array<String>) {
	runApplication<ReadbookSecurityApplication>(*args)
}
