package com.project.contact.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class UserInfoDto {
	@NotBlank(message =  "user name should not be null of blank")
	@Pattern(regexp = "^[a-zA-Z0-9 ]*$",message = "userName should only alpha numeric")
	private String  userName;
	private String email;
	@NotBlank(message =  "user name should not be null of blank")
	private String password;
	@NotBlank(message =  "user name should not be null of blank")
	@Pattern(regexp = "^[A-Z_,]*$",message = "role should only contain capital letter and comma")
	private String role;
}
