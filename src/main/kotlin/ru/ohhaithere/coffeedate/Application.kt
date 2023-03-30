package ru.ohhaithere.coffeedate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}