package com.poisonedyouth.realdatabasedemo

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RealDatabaseDemoApplication

fun main(args: Array<String>) {
    runApplication<RealDatabaseDemoApplication>(*args)
}
