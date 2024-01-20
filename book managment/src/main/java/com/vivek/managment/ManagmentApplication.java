package com.vivek.managment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class ManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagmentApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			log.warn("hello world!");
		};
	}

}
