package com.webservices.entity;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Message {

	private HttpStatus status;
	@JsonIgnore
	private String msg;

	private List<String> errors;

	public Message(HttpStatus status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public Message() {
		super();
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return msg;
	}

	public void setMessage(String message) {
		this.msg = message;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "Message [status=" + status + ", msg=" + msg + "]";
	}

}
