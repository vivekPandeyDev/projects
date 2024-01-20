package com.webservices.entity;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.webservices.enums.Qualification;
import com.webservices.formatter.LocalDateDeserializer;
import com.webservices.formatter.LocalDateSerializer;

public class Employee {
	@Pattern(regexp = "[a-zA-Z][a-zA-Z ]+", message = "name should only contain alphabets")
	private String name;
	private Qualification qualification;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfJoining;
	@Email
	private String email;
	@Length(min = 10, max = 10, message = "contact number should be of size 10")
	private String contactNo;
	private String designation;

	public Employee() {
		super();
	}

	public Employee(String name, Qualification qualification, LocalDate dateOfJoining, String email, String contactNo,
			String designation) {
		super();
		this.name = name;
		this.qualification = qualification;
		this.dateOfJoining = dateOfJoining;
		this.email = email;
		this.contactNo = contactNo;
		this.designation = designation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "Employee [name:" + name + ", qualification:" + qualification + ", dateOfJoining:" + dateOfJoining
				+ ", email:" + email + ", contactNo:" + contactNo + ", designation:" + designation + "]";
	}

}
