package com.xxd.openFeign1.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient(name = "openFeign-server2")
public interface DemoService {
    //调用服务提供方的输出接口
    @GetMapping(value = "/test1")
    String test1();
    @GetMapping(value = "/test2")
    String test2();
}