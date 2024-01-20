package com.vivek.pandey.api.movie.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException {
    private  HttpStatus status = HttpStatus.BAD_REQUEST;
    private  String title = "SERVICE EXCEPTION";

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ServiceException(String message, String title) {
        super(message);
        this.title = title;
    }

    public ServiceException(String message, HttpStatus status, String title) {
        this(message,status);
        this.title = title;
    }
}
