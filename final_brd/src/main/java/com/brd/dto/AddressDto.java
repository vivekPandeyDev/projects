package com.brd.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class AddressDto {
	@NotBlank
	@Length(max = 100, message = "length greater then 100")
	private String address1;
	@Length(max = 100, message = "length greater then 100")
	private String address2;
	@Min(100000)
	@Max(999999)
	private int pincode;
}
