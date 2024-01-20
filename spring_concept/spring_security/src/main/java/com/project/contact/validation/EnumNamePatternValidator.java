package com.project.contact.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Objects;

public class EnumNamePatternValidator implements ConstraintValidator<EnumNamePattern, String> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(EnumNamePattern constraintAnnotation) {
        enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Null values are considered valid
        }
        Object[] enumValues = enumClass.getEnumConstants();

        return Arrays.stream(enumValues)
                .map(Objects::toString)
                .anyMatch(s -> s.equals(value));
    }
}
