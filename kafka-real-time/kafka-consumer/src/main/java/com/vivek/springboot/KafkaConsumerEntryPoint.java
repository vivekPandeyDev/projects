package com.vivek.springboot;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaConsumerEntryPoint {
    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerEntryPoint.class,args);
    }
    @Bean
    ModelMapper mapper(){
        return new ModelMapper();
    }
    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
