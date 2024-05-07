package com.springboot.university.validator;

import com.springboot.university.annotation.SizeValidator;
import com.springboot.university.requestresponse.request.UniversityRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SizeMatchValidator implements ConstraintValidator<SizeValidator, UniversityRequestDto> {
    @Override
    public void initialize(SizeValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UniversityRequestDto universityRequestDto, ConstraintValidatorContext constraintValidatorContext) {
        if (universityRequestDto == null) {
            return true;
        }
        return universityRequestDto.getNumberOfColleges() == universityRequestDto.getListOfColleges().size();
    }
}
