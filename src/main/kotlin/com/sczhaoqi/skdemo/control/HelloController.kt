package com.sczhaoqi.skdemo.control

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("helloController")
class HelloController {
    @GetMapping("/")
    fun hello(): String {
        return "Hello Kotlin!";
    }
    @GetMapping("/a")
    fun a(): String {
        return "Hello Kotlin!";
    }
}