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
	@NotBlank(message = "{student.invalid.name.blank}")
	@Pattern(regexp = "^[a-zA-Z0-9 ]*$",message = "{student.invalid.name.pattern}")
	private String name;
	@Email(message = "{student.invalid.email.pattern}")
	private String email;
	@NotBlank(message = "{student.invalid.name.qualification}")
	private String qualification;
	private int id;
}
