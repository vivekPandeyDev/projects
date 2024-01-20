package com.vivek.pandey.api.movie.config;

import com.vivek.pandey.api.movie.formatter.SpaceToUnderscoreFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class GlobalWebConfig implements WebMvcConfigurer {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        final var threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        final var processorCount = Runtime.getRuntime().availableProcessors();
        threadPoolTaskExecutor.setCorePoolSize(processorCount);
        threadPoolTaskExecutor.setMaxPoolSize(processorCount*2);
        threadPoolTaskExecutor.setQueueCapacity(400);
        threadPoolTaskExecutor.setThreadNamePrefix("custom-thread-pool-");
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.setAwaitTerminationSeconds(60);
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.setAwaitTerminationSeconds(60);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(new SpaceToUnderscoreFormatter());
    }
}
