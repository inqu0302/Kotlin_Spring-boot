package com.callor.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * web.xml 파일을 대신하는 boot 클래스 만들기
 */
@SpringBootApplication
class KSpringApplication

fun main(args: Array<String>) {
	runApplication<KSpringApplication>(*args)
}
