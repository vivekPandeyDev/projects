package com.microsoft.hackthon.exception;

import lombok.Getter;

import java.io.Serial;

import com.microsoft.hackthon.dto.error.response.ErrorResponse;

@Getter
public class CustomUserException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 3460811975810873127L;
	private final ErrorResponse errorResponse;

	public CustomUserException(ErrorResponse errorResponse) {
		super(errorResponse.getMessage());
		this.errorResponse = errorResponse;
	}
}
