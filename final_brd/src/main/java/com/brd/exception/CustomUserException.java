package com.brd.exception;

public class CustomUserException extends RuntimeException {

	private static final long serialVersionUID = 5438264091392493828L;

	public CustomUserException(String message) {
		super(message);
	}
}
