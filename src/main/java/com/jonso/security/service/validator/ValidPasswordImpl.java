package com.jonso.security.service.validator;

import com.jonso.security.error.AppErrors;
import com.jonso.security.error.AppException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidPasswordImpl implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.isEmpty() || value.length() < 6)
            throw new AppException(AppErrors.PASSWORD_NOT_VALID);

        return true;
    }
}
