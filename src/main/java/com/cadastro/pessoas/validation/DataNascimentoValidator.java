package com.cadastro.pessoas.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DataNascimentoValidator implements ConstraintValidator<DataNascimentoValida, LocalDate> {

    @Override
    public boolean isValid(LocalDate data, ConstraintValidatorContext context) {
        if (data == null) return true;

        if (data.isAfter(LocalDate.now())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Data de nascimento não pode ser futura")
                    .addConstraintViolation();
            return false;
        }

        if (data.isBefore(LocalDate.now().minusYears(120))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Data de nascimento não pode ser anterior a 120 anos")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
