package com.xxd.openFeign1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Demo1Controller {

    @Resource
    DemoService demoService;

    @GetMapping("/test2")
    public String test() {
        return demoService.test();
    }
}