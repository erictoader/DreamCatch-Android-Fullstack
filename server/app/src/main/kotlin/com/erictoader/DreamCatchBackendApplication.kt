package com.erictoader

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class DreamCatchBackendApplication

fun main(args: Array<String>) {
    runApplication<DreamCatchBackendApplication>(*args)
}
