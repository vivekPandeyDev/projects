package com.brd.exception;

public class CustomerException extends RuntimeException {


	private static final long serialVersionUID = 1352581518450882857L;

	public CustomerException(String message, Throwable cause) {
        super(message, cause);
    }
}
