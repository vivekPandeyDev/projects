package com.transaction.practice.runner;

import com.transaction.practice.config.AppConfiguration;
import com.transaction.practice.config.HibernateConfiguration;
import com.transaction.practice.entity.Product;
import com.transaction.practice.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Log4j2
public class ConfigRunner {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(HibernateConfiguration.class, AppConfiguration.class);
        applicationContext.registerShutdownHook();
        ProductService productService =  applicationContext.getBean("productService", ProductService.class);
//        productService.save(new Product("test product"));
//        productService.saveMultiple();
        productService.saveMultipleMandatory();
//        productService.getProduct(10000);
        applicationContext.close();
    }
}
