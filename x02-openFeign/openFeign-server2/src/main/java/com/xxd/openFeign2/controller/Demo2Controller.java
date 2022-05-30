package com.xxd.openFeign2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo2Controller {

    @GetMapping("/test1")
    public String test1() {
        return "这是服务2的接口" ;
    }

    @GetMapping("/test2")
    public String test2() throws Exception {
        throw new Exception();
    }
}