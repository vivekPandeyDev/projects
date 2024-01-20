package com.brd.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.brd.exception.CustomMakerException;
import com.brd.exception.CustomUserException;

@ControllerAdvice
public class AdviceController {
	@ExceptionHandler(value = { CustomUserException.class,CustomMakerException.class})
	public String userExceptionHandler() {
		return "505";
	}
}
