package com.webservices.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.webservices.entity.Message;
import com.webservices.exception.UnAvailableException;

@RestControllerAdvice
public class EmployeeAdvice {
	@ExceptionHandler(UnAvailableException.class)
	public Message customExeption(UnAvailableException exception) {
		return new Message(exception.getStatus(), exception.getMessage());
	}
}
