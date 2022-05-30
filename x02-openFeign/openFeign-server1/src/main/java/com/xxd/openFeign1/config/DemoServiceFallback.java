package com.xxd.openFeign1.config;

import com.xxd.openFeign1.controller.DemoService;

public class DemoServiceFallback implements DemoService {
    private Throwable throwable;

    DemoServiceFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String test() {
        return "exception:" + throwable.getMessage();
    }
}