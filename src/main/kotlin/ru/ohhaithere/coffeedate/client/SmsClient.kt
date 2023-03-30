package ru.ohhaithere.coffeedate.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import ru.ohhaithere.coffeedate.client.dto.SmsSendDto

@FeignClient(value = "smsclient", url = "https://frostmasterwork@gmail.com:z5ctNVpukUEAgWq_5c0kJhXjVtqu9L@gate.smsaero.ru/v2")
interface SmsClient {

    @PostMapping("/sms/send")
    fun sendSms(@RequestBody dto: SmsSendDto)

}