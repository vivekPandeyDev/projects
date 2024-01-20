package com.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = NameValidatorConstraint.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {
	String message() default "{com.spring.dto.name}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
