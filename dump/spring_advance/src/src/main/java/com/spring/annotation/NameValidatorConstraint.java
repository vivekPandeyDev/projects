package com.spring.annotation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidatorConstraint implements ConstraintValidator<Name, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return isContainSpecialCharacter(value) == false;
	}
	
	
	public boolean isContainSpecialCharacter(String value) {
		if(value == null) {
			return true;
		}
		
		Pattern checkPattern = Pattern.compile("[^a-zA-Z ]");
		// Declare matcher to match with String
		Matcher match = checkPattern.matcher(value);
		// Use find( ) function to check
		return match.find();
	}
	

}
