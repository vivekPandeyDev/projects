package com.project.contact.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JsonResponse<T> {
	private T data;
	private HttpStatus status;
    private String message;
}
