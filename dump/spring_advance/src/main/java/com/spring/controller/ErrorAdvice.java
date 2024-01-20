package com.spring.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorAdvice {
	@ExceptionHandler(NoHandlerFoundException.class)
	public String handle(Exception ex) {
		return "redirect:/404";
	}


}
