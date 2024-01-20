package com.project.contact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:my-config.properties")
public class ContactApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactApplication.class, args);
	}

}
