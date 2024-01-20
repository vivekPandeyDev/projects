package com.project.contact.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
	@NotNull(message = "{user.invalid.name.blank}")
	private String userName;
	@NotNull(message = "{user.invalid.password.blank}")
	private String password;
}
