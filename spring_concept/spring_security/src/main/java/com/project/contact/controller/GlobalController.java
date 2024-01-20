package com.project.contact.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.project.contact.dto.ApiResponse;
import com.project.contact.dto.ErrorResponse;
import com.project.contact.exception.CustomException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@RestControllerAdvice
public class GlobalController {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customUserExceptionHandler(CustomException userException) {
        ErrorResponse errorResponse = userException.getErrorResponse();
        return ResponseEntity.status(userException.getErrorResponse().getStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException errors) {
        // Handle the validation exception and generate a response
        List<String> validationErrors = errors.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .toList();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST).message("not a valid dto".toUpperCase(Locale.ROOT))
                .errors(validationErrors)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ErrorResponse> handleJsonParseException(JsonParseException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("INVALID JSON")
                .errors(Collections.singletonList("JSON parsing error: " + ex.getOriginalMessage())).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    private String getRequestPath() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getRequestURI();
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse> badCredentialsException(BadCredentialsException ex){
        ApiResponse errorResponse = ApiResponse.builder()
                .path(getRequestPath())
                .statusCode(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> accessDeniedException(AccessDeniedException ex){
        ApiResponse errorResponse = ApiResponse.builder()
                .path(getRequestPath())
                .statusCode(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ApiResponse> signatureException(SignatureException ex){
        ApiResponse errorResponse = ApiResponse.builder()
                .path(getRequestPath())
                .statusCode(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponse> expiredJwtException(ExpiredJwtException ex){
        ApiResponse errorResponse = ApiResponse.builder()
                .path(getRequestPath())
                .statusCode(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .message("jwt token expired, login to get new access token")
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }



}
