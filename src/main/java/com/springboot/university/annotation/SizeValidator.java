package com.springboot.university.annotation;

import com.springboot.university.validator.SizeMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SizeMatchValidator.class)
public @interface SizeValidator {
    String message() default "Number of colleges does not match the list of colleges";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}