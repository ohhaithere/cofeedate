package ru.ohhaithere.coffeedate.client

import feign.Headers
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*
import ru.ohhaithere.coffeedate.client.dto.SmsSendDto

@FeignClient(value = "smsClient", url = "http://gate.smsaero.ru/v2")
interface SmsClient {

    @GetMapping("/sms/send?number={phone}&text={text}&sign=SMS+Aero")
    fun sendSms(@RequestHeader("Authorization") header: String, @PathVariable phone: String, @PathVariable text: String)

}