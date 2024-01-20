package com.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = AgeValidatorConstraint.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Age {
	int min() default 18;

	int max() default 60;

	String message() default "{com.spring.dto.age}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
