package com.spring.dto;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerDto {
	@NotBlank(message = "first name cannot be blank")
	@Size(min = 3, max = 20, message = "first name should have at least 3 char")
	private String firstName;
	@NotBlank(message = "last name cannot be blank")
	@Size(min = 3, max = 20, message = "last name should have at least 3 char")
	private String lastName;
	private String nationality;
	@NotNull(message = "gender cannot be null")
	private String gender;
	@NotNull(message = "dob cannot be null")
	private LocalDate dob;
	@Valid
	private AddressDto address;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "CustomerDto [firstName=" + firstName + ", lastName=" + lastName + ", nationality=" + nationality
				+ ", gender=" + gender + ", dob=" + dob + ", address=" + address + "]";
	}

}
