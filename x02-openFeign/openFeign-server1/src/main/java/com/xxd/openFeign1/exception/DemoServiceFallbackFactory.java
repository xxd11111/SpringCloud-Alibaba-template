package com.xxd.openFeign1.exception;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class DemoServiceFallbackFactory implements FallbackFactory<DemoServiceFallback> {
    @Override
    public DemoServiceFallback create(Throwable throwable) {
        return new DemoServiceFallback(throwable);
    }
}