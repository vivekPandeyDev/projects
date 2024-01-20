package com.project.contact.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 8893556836963108102L;
	private HttpStatus status;
    private String message;
    private List<String> errors;
	
}
