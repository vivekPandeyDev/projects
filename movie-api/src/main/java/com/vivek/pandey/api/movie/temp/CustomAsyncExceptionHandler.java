package com.vivek.pandey.api.movie.temp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

@Slf4j
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        log.error("Async method exception: " + throwable.getMessage());
        throwable.getStackTrace();
    }
}
