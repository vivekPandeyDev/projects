package com.vivek.springboot;

import com.vivek.springboot.service.WikimediaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaProducerEntryPoint {
    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerEntryPoint.class,args);
    }

    @Autowired
    private WikimediaProducer wikimediaProducer;

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            wikimediaProducer.sendMessage();
        };
    }
}
