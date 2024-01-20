package com.project.contact.exception;

import com.project.contact.dto.ErrorResponse;

import lombok.Getter;

import java.io.Serial;

@Getter
public class CustomException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 3460811975810873127L;
	private final ErrorResponse errorResponse;

	public CustomException(ErrorResponse errorResponse) {
		super(errorResponse.getMessage());
		this.errorResponse = errorResponse;
	}
}
