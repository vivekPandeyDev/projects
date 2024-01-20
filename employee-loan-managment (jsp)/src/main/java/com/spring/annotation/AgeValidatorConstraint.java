package com.spring.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidatorConstraint implements ConstraintValidator<Age, Integer> {
	private int min;
	private int max;

	@Override
	public void initialize(Age age) {
		this.min = age.min();
		this.max = age.max();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		if (value < min || value > max) {
			return false;
		}
		return true;

	}

}
