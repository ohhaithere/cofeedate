package ru.ohhaithere.coffeedate.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*
import ru.ohhaithere.coffeedate.client.dto.SmsResponseDto

@FeignClient(value = "smsClient", url = "\${client.sms}")
interface SmsClient {

    @GetMapping("/sms/send?number={phone}&text={text}&sign=SMS+Aero")
    fun sendSms(@RequestHeader("Authorization") header: String, @PathVariable phone: String, @PathVariable text: String): SmsResponseDto

}