package com.darkhold.darkholdapi.controller

import com.darkhold.darkholdapi.classes.HtmlExtractionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {

    @GetMapping
    @RequestMapping("/welcome")
    fun home(): String = "Welcome to my API, I am Thai Rodrigues"

    @GetMapping
    @RequestMapping("/fii")
    fun fii(): String = HtmlExtractionService().extract()
}
