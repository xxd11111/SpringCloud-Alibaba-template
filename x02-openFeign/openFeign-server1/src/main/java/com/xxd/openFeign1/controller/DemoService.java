package com.xxd.openFeign1.controller;

import com.xxd.openFeign1.config.DemoServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "openFeign-server2", fallbackFactory = DemoServiceFallbackFactory.class)
public interface DemoService {

    /**
     * 调用服务提供方的输出接口
     *
     * @return
     */
    @GetMapping(value = "/test")
    String test();
}