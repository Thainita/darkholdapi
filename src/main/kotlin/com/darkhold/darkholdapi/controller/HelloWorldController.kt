package com.darkhold.darkholdapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageResource {

    @GetMapping
    fun home(): String = "Hello world"
}
