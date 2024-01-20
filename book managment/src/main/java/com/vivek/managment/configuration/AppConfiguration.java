package com.vivek.managment.configuration;

import com.vivek.managment.formatter.LocalDateFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfiguration implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new LocalDateFormatter());
    }
    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
