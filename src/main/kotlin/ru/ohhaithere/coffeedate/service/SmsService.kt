package ru.ohhaithere.coffeedate.service

import org.springframework.stereotype.Component
import ru.ohhaithere.coffeedate.client.RandomOrgFeignClient
import ru.ohhaithere.coffeedate.client.SmsClient
import ru.ohhaithere.coffeedate.dto.UserCreateDto
import kotlin.random.Random
import org.bouncycastle.cms.RecipientId.password

import org.apache.tomcat.jni.User.username
import org.bouncycastle.cms.RecipientId

import org.springframework.util.Base64Utils





@Component
class SmsService(private var smsClient: SmsClient,
                 private var randomOrgClient: RandomOrgFeignClient
) {

        fun sendSmsWithCode(userCreateDto: UserCreateDto): String {
            val r = Random
            val code = java.lang.String.format("%04d", r.nextInt(1001))
            val encodedBytes = Base64Utils.encode(("frostmasterwork@gmail.com:z-z5ctNVpukUEAgWq_5c0kJhXjVtqu9L").toByteArray())
            val authHeader = "Basic " + String(encodedBytes)
            smsClient.sendSms(authHeader, userCreateDto.phone, code);
            return code;
        }

}