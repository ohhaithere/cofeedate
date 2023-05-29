package ru.ohhaithere.coffeedate.configuration

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.multipart.commons.CommonsMultipartResolver

import org.springframework.web.multipart.MultipartResolver




@Component
class Config {

    @Bean
    fun multipartResolver(): MultipartResolver? {
        val multipartResolver = CommonsMultipartResolver()
        multipartResolver.setMaxUploadSize(100000000)
        return multipartResolver
    }

}