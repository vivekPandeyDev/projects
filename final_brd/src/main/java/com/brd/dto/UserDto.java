package com.brd.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserDto {
	@Pattern(regexp = "^[a-zA-Z0-9_]*$",message="only alphanumeric allowed")
	@Length(min = 5, message="length greater than 5 needed")
	private String username;
	@Length(min = 5, message="length greater than 5 needed")
	private String password;
	@Email(message = "enter valid email")
	private String email;
	
}
