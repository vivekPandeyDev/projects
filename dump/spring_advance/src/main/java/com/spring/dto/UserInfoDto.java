package com.spring.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.spring.annotation.Age;
import com.spring.annotation.Name;

public class UserInfoDto {
	@Name
	@NotBlank
	private String userName;
	@NotBlank
	@Size(min=12,max=60)
	private String password;
	@Age(min = 18, max = 50)
	private Integer age;
	LocalDate dob;
	
	
	

	public UserInfoDto() {
		super();
//		this is default value
		userName = "vivek pandey";
		password = "vivek 123";
		age = 25;
		dob = LocalDate.of(2000, 7, 12);
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserInfoDto [userName=" + userName + ", password=" + password + ", age=" + age + "]";
	}

}
