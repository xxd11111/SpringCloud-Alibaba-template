package com.xxd.server1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Description:
 * @author: xxd
 * @date: 2022.05.30
 */
@RestController
public class DemoController {
    @Resource
    private RestTemplate restTemplate;
    @GetMapping()
    public String test(){
        return restTemplate.getForObject("http://localhost:9202/", String.class);
    }
}
