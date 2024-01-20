package com.project.contact.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {
	@NotBlank(message = "student name cannot be blank")
	@Pattern(regexp = "^[a-zA-Z0-9 ]*$",message = "role should only alpha numeric")
	private String name;
	@Email(message = "not a valid email")
	private String email;
	@NotBlank(message = "student qualification cannot be blank")
	private String qualification;
	private int id;
}
