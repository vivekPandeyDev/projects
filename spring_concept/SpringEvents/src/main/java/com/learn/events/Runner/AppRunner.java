package com.learn.events.Runner;

import com.learn.events.config.AppConfiguration;
import com.learn.events.publisher.ZeeCafePublisher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppRunner {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ZeeCafePublisher zeeCafePublisher =  context.getBean("zeeCafePublisher", ZeeCafePublisher.class);
        zeeCafePublisher.streamBigBangTheory("ep-001");
        zeeCafePublisher.streamDemonSlayer("ep-001");
    }
}
