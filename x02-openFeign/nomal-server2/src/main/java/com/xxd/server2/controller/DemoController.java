package com.xxd.server2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: xxd
 * @date: 2022.05.30
 */
@RestController
public class DemoController {
    @GetMapping()
    public String test(){
        return "这是server2的信息";
    }
}
