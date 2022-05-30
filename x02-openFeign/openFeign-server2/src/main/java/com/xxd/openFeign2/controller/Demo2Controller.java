package com.xxd.openFeign2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo2Controller {

    @GetMapping("/test")
    public String test() {
        return "这是服务2的接口" ;
    }

}