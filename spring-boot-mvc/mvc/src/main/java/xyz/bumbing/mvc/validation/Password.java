package xyz.bumbing.mvc.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // 1
@Retention(RetentionPolicy.RUNTIME) // 2
@Constraint(validatedBy = PasswordValidator.class) // 3
public @interface Password {

    String message() default "invalid password"; // 4

    Class[] groups() default {};

    Class[] payload() default {};
}
