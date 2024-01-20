package com.project.contact.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiResponse {

    private String message;
    private String path;
    private LocalDateTime timestamp;
    private HttpStatus statusCode;
}





