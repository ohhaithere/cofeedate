package ru.ohhaithere.coffeedate.service

import org.springframework.stereotype.Component
import ru.ohhaithere.coffeedate.client.SmsClient
import kotlin.random.Random

import org.springframework.util.Base64Utils

@Component
class SmsService(private var smsClient: SmsClient
) {

        fun sendSmsWithCode(phone: String): String {
            val r = Random
            val code = java.lang.String.format("%04d", r.nextInt(1001))
            val encodedBytes = Base64Utils.encode(("frostmasterwork@gmail.com:z-z5ctNVpukUEAgWq_5c0kJhXjVtqu9L").toByteArray())
            val authHeader = "Basic " + String(encodedBytes)
            val response = smsClient.sendSms(authHeader, phone, code);
            return code;
        }

}