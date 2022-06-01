package com.xxd.sentinel.controller.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo1Controller {

    @GetMapping("/test1")
    public String test1() {
        return "test1";
    }
}