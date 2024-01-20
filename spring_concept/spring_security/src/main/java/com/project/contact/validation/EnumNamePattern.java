package com.project.contact.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumNamePatternValidator.class)
public @interface EnumNamePattern {

    String message() default "Invalid enum value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum<?>> enumClass();

}