package com.vivek.managment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> {
    private List<T> data;
    private HttpStatus status;
    private String message;
}
