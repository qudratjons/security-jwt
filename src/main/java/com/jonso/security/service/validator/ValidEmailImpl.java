package com.jonso.security.service.validator;

import com.jonso.security.error.AppErrors;
import com.jonso.security.error.AppException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidEmailImpl implements ConstraintValidator<ValidEmail, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?!-)(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        if (value == null || value.isEmpty() || value.isBlank() || !value.matches(emailRegex))
            throw new AppException(AppErrors.EMAIL_NOT_VALID);

        return true;
    }
}
