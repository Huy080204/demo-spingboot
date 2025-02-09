package com.example.demo.validation;

import com.example.demo.validation.impl.GenderValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GenderValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidGender {
    String message() default "Invalid gender! Only accepted values: 1 (MALE), 2 (FEMALE), 3 (OTHER)";

    boolean allowNull() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
