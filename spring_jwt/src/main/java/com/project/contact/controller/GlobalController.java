package com.project.contact.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.contact.dto.ErrorResponse;
import com.project.contact.exception.CustomUserException;

@RestControllerAdvice
public class GlobalController {
	
	@ExceptionHandler(CustomUserException.class)
	public ResponseEntity<ErrorResponse> customUserExceptionHandler(CustomUserException userException){
		ErrorResponse errorResponse =  userException.getErrorResponse();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
}
