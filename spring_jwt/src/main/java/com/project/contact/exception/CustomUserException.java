package com.project.contact.exception;

import com.project.contact.dto.ErrorResponse;

import lombok.Getter;

@Getter
public class CustomUserException extends RuntimeException {

	private static final long serialVersionUID = 3460811975810873127L;
	private final ErrorResponse errorResponse;

	public CustomUserException(ErrorResponse errorResponse) {
		super(errorResponse.getMessage());
		this.errorResponse = errorResponse;
	}
}
