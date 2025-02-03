package spring.biblioteca.modelo;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Constraint(validatedBy = DNIValidator.class)
@Documented
@interface DNIValido {

    String message() default "DNI invalido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}

public class DNIValidator implements ConstraintValidator<DNIValido,String> {
    private String regx = "/^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$/i";
    private String dni;

    @Override
    public void initialize(DNIValido constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    return dni.matches(regx);
    }
}
