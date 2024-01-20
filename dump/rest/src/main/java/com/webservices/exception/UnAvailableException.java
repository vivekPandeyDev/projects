package com.webservices.exception;

import org.springframework.http.HttpStatus;

public class UnAvailableException extends RuntimeException {

	private static final long serialVersionUID = 7148557714957887562L;
	private HttpStatus status;

	public UnAvailableException(HttpStatus status,String message) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	

}
