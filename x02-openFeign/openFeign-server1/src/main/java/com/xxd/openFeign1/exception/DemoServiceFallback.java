package com.xxd.openFeign1.exception;

import com.xxd.openFeign1.controller.DemoService;

public class DemoServiceFallback implements DemoService {
    private Throwable throwable;

    DemoServiceFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String test1() {
        return "test1调用错误:" + throwable.getMessage();
    }

    @Override
    public String test2() {
        return "test2调用错误:" + throwable.getMessage();
    }
}