package ru.ohhaithere.coffeedate.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(value = "randomClient", url = "http://www.random.org/")

interface RandomOrgFeignClient {

    @GetMapping("strings/?num=1&len=4&digits=on&unique=on&format=plain&rnd=new")
    fun getCode(): String

}