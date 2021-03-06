package com.xxd.openFeign1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description:
 * @author: xxd
 * @date: 2022.05.30
 */
@SpringBootApplication
@EnableFeignClients
public class OpenFeign1Application {
    public static void main(String[] args) {
        SpringApplication.run(OpenFeign1Application.class, args);
    }
}
