package com.xxd.openFeign1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Demo1Controller {
    @Resource
    DemoService demoService;

    @GetMapping("/test1")
    public String test1() {
        return demoService.test1();
    }
    @GetMapping("/test2")
    public String test2() {
        String s;
        try {
            s = demoService.test2();
        }catch (Exception e){
            s = "error";
        }
        return s;
    }
}