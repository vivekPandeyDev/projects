package com.microsoft.hackthon.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
	private String userName;
	private String email;
	private String role;
}
