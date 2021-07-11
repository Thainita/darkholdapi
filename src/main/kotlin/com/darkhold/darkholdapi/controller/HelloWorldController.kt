package com.darkhold.darkholdapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/welcome")
class HelloWorldController {

    @GetMapping
    fun home(): String = "Welcome to my API, I am Thai Rodrigues"
}
