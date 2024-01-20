package com.project.contact.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
	@NotNull(message = "user name cannot be null")
	private String userName;
	@NotNull(message = "user password canot be null")
	private String password;
}
