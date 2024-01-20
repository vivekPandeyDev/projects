package com.brd.config;

import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.brd.formatter.LocalDateFormatter;

@Configuration
@EnableWebMvc
@ComponentScan("com.brd")
public class AppConfiguration implements WebMvcConfigurer {
	@Bean
	ModelMapper mapper() {
		return new ModelMapper();
	}
	
	@Bean
	CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver =  new CommonsMultipartResolver();
		resolver.setMaxUploadSize(10000000);
		return resolver;
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setSuffix(".jsp");
		resolver.setPrefix("/WEB-INF/views/");
		return resolver;
	}

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.US);
		cookieLocaleResolver.setCookieName("lang");
		return cookieLocaleResolver;
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new LocalDateFormatter());
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.addBasenames("messages", "language");
		return source;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		factory.setValidationMessageSource(messageSource());
		return factory;

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/public/");
	}

	@Override
	public org.springframework.validation.Validator getValidator() {
		return validator();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

}
