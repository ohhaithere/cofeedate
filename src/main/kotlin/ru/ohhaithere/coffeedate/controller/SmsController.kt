package ru.ohhaithere.coffeedate.controller

import org.springframework.web.bind.annotation.*
import ru.ohhaithere.coffeedate.dto.UserSmsRegisterDto

@RestController
@CrossOrigin
@RequestMapping("/sms")
interface SmsController {

    @PostMapping()
    fun getSms(@RequestBody smsCreateDto: UserSmsRegisterDto) {

    }

}