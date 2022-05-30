package com.xxd.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xxd
 * @description 用于测试配置刷新
 * @date 2022-05-29
 */
@RefreshScope
@RestController
public class DemoController {
    @Value("${user.name}")
    String userName;
    @Value("${user.age}")
    int age;

    @GetMapping("test")
    public String test() {
        return "userName:" + userName + ",age:" + age;
    }
}
