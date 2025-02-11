package com.example.demo.validation.impl;

import com.example.demo.constant.GenderConstants;
import com.example.demo.enumeration.Gender;
import com.example.demo.validation.ValidGender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.EnumSet;
import java.util.Set;

public class GenderValidator implements ConstraintValidator<ValidGender, Gender> {
    private boolean allowNull;

    @Override
    public void initialize(ValidGender constraintAnnotation) {
        this.allowNull = constraintAnnotation.allowNull();
    }

    @Override
    public boolean isValid(Gender value, ConstraintValidatorContext context) {
        if (value == null) {
            return allowNull;
        }
        return GenderConstants.VALID_GENDERS.contains(value);
    }
}
