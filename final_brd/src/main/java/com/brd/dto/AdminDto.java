package com.brd.dto;

import java.util.List;

import lombok.Data;

@Data
public class AdminDto {
	private String username;
	private List<String> roles;
}
