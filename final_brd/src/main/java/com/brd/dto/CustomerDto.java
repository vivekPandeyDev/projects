package com.brd.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.brd.enums.CustomerFlag;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDto {
	private String customerCode;
	@Pattern(regexp = "^[a-zA-Z0-9_ ]*$",message = "customer name only contain alpha numeric")
	@NotBlank
	@Length(max = 30, message = "length greater then 30")
	private String customerName;
	@Valid
	private AddressDto address;
	@Email(message = "not a valid email")
	@NotBlank
	@Length(max = 100, message = "length greater then 100")
	private String customerEmail;
	@Length(max = 20, message = "length greater then 20")
	private String contactNumber;
	@NotBlank
	@Length(max = 100, message = "length greater then 100")
	private String primaryContactNumber;
	private CustomerFlag flag;
}
