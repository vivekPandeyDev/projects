package com.webservices.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.webservices.formatter.LocalDateFormatter;

@Configuration
@EnableWebMvc
@ComponentScan("com.webservices")
public class AppConfiguration extends WebMvcConfigurationSupport {
	@Override
	protected void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new LocalDateFormatter());
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.addBasenames("validation","exception-messages");
		return source;
	}
	
	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		factory.setValidationMessageSource(messageSource());
		return factory;
	}
	
	@Override
	protected Validator getValidator() {
		return validator();
	}
}
