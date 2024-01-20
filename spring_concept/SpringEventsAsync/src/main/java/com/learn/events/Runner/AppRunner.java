package com.learn.events.Runner;

import com.learn.events.config.AppConfiguration;
import com.learn.events.publisher.Gpay;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AppRunner {
    public static void main(String[] args) {
        ApplicationContext context =new AnnotationConfigApplicationContext(AppConfiguration.class);
        Gpay gPay =  context.getBean("gpay",Gpay.class);
        gPay.sendMoney("vivek pandey",12000D,false);
        gPay.sendMoney("vishal pandey",15000D,false);

    }
}
