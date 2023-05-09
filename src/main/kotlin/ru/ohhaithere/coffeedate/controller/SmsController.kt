package ru.ohhaithere.coffeedate.controller

import org.springframework.web.bind.annotation.*
import ru.ohhaithere.coffeedate.dto.UserSmsRegisterDto
import ru.ohhaithere.coffeedate.service.SmsService

@RestController
@CrossOrigin
@RequestMapping("/sms")
class SmsController(val smsService: SmsService) {



    @PostMapping()
    fun getSms(@RequestBody smsCreateDto: UserSmsRegisterDto) {

    }

}