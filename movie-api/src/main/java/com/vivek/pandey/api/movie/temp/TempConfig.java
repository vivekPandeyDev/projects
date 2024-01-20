package com.vivek.pandey.api.movie.temp;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

@Configuration
public class TempConfig implements AsyncConfigurer {

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        // or use custom-made handler but simple one might be better and no need to configure
        // it also gives stack trace
        return new SimpleAsyncUncaughtExceptionHandler();
    }

}
