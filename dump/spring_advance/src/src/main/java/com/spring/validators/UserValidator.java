package com.spring.validators;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.spring.dto.UserDto;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserDto dto = (UserDto)target;
		if( dto.getHobbies().length < 2) {
			errors.rejectValue("hobbies", "com.spring.dto.hobbies");
		}
		if(dto.getDob() !=null && getAge(dto.getDob()) < 18) {
			errors.rejectValue("age", "com.spring.dto.age");
		}
		
	}
	
	int getAge(LocalDate dob) {
		
		return LocalDate.now().getYear() -  dob.getYear(); 
	}

}
