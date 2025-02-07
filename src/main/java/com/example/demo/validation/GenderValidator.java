package com.example.demo.validation;

import com.example.demo.enumeration.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.EnumSet;
import java.util.Set;

public class GenderValidator implements ConstraintValidator<ValidGender, Gender> {
    private boolean allowNull;
    private static final Set<Gender> VALID_GENDERS = EnumSet.allOf(Gender.class);

    @Override
    public void initialize(ValidGender constraintAnnotation) {
        this.allowNull = constraintAnnotation.allowNull();
    }

    @Override
    public boolean isValid(Gender value, ConstraintValidatorContext context) {
        if (value == null) {
            return allowNull;
        }
        return VALID_GENDERS.contains(value);
    }
}
