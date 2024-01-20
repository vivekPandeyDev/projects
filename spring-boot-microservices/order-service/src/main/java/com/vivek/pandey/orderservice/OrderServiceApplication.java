package com.vivek.pandey.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@Configuration
public class OrderServiceApplication {

	@Bean
	WebClient webclient(){
		return WebClient.builder().build();
	}


	public static void main(String[] args) {

		SpringApplication.run(OrderServiceApplication.class, args);
	}


}
