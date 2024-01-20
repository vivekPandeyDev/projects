package com.vanlife.manage.controller;

import io.jsonwebtoken.JwtException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class UnauthorizedRequestExceptionHandler  {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleUnauthorizedRequestException(AuthenticationException ex) {
        // Create a custom error response
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Unauthorized", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Object> handleJwtException(JwtException ex) {
        System.out.println("jwt exception: "+ex.getMessage());
        // Create a custom error response
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Unauthorized", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
    // Optionally, you can handle other exceptions as well

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        // Create a custom error response
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Define ErrorResponse class if not already defined

    @Data
    public static class ErrorResponse {
        private HttpStatus status;
        private String error;
        private String message;

        public ErrorResponse(HttpStatus status, String error, String message) {
            this.status = status;
            this.error = error;
            this.message = message;
        }

    }
}

