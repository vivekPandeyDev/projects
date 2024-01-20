package com.transaction.practice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.transaction.practice")
@EnableAspectJAutoProxy
public class AppConfiguration {
}
