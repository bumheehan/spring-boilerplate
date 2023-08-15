package xyz.bumbing.mvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> { // 1

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) { // 2
        if (value == null) {
            return false;
        }

        return value.matches("^(?=.*[A-Za-z])(?=.*[!\"#$%&'()*+,\\-./:;<=>?@\\[\\]^_`{|}~\\\\])(?=.*[0-9])[0-9A-Za-z!\"#$%&'()*+,\\-./:;<=>?@\\[\\]^_`{|}~\\\\]{8,16}$");
    }
}