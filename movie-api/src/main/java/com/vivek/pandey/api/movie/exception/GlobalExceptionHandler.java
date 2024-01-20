package com.vivek.pandey.api.movie.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(FileNotFoundException.class)
    ProblemDetail handleFileNotFound(FileNotFoundException ex, HttpServletRequest request){
        ProblemDetail problemDetail =  ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        problemDetail.setTitle("CANNOT FIND POSTER IMAGE");
        problemDetail.setType(ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).path("error").build().toUri());
        logMessage(ex.getMessage());
        return  problemDetail;
    }

    @ExceptionHandler(ServiceException.class)
    ProblemDetail handleMovieNotProblemDetail(ServiceException ex, HttpServletRequest request){
        ProblemDetail problemDetail =  ProblemDetail.forStatusAndDetail(ex.getStatus(), ex.getMessage());
        problemDetail.setTitle(ex.getTitle());
        problemDetail.setType(ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).path("error").build().toUri());
        logMessage(ex.getMessage());
        return  problemDetail;
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ProblemDetail handleMissingServletRequestPartException(MissingServletRequestPartException ex,HttpServletRequest request) {
        ProblemDetail problemDetail =  ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        problemDetail.setTitle("NO FILE FOUND");
        problemDetail.setType(ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).path("error").build().toUri());
        logMessage(ex.getMessage());
        return  problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleConstraintViolationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(PropertyReferenceException.class)
    public ProblemDetail invalidPropertyException(PropertyReferenceException ex,HttpServletRequest request) {
        String propertyName = extractPropertyName(ex);
        String message = "Invalid property: " + propertyName;
        ProblemDetail problemDetail =  ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message);
        problemDetail.setTitle("INVALID PROPERTY");
        problemDetail.setType(ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).path("error").build().toUri());
        logMessage(ex.getMessage());
        return  problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail invalidPropertyException(Exception ex,HttpServletRequest request) {
        ProblemDetail problemDetail =  ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        problemDetail.setTitle("SERVER ERROR");
        problemDetail.setType(ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).path("error").build().toUri());
        logMessage(ex.getMessage());
        return  problemDetail;
    }

    private String extractPropertyName(PropertyReferenceException ex) {
        String message = ex.getMessage();
        int startIndex = message.indexOf("'") + 1;
        int endIndex = message.lastIndexOf("'");
        return message.substring(startIndex, endIndex);
    }

    private void logMessage(String message){
        log.info("**************************** Exception Handled Start ****************************");
        log.error("Error message: {}",message);
        log.info("**************************** Exception Handled End ****************************");
    }
}
