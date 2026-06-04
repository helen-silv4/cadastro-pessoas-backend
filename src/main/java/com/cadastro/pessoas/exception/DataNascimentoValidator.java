package com.cadastro.pessoas.exception;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DataNascimentoValidator
        implements ConstraintValidator<DataNascimentoValida, LocalDate> {

    @Override
    public boolean isValid(LocalDate data, ConstraintValidatorContext context) {

        if (data == null) {
            return false;
        }

        LocalDate hoje = LocalDate.now();

        // não pode ser futura
        if (data.isAfter(hoje)) {
            return false;
        }

        // não pode ser anterior a 120 anos atrás
        LocalDate limiteMaximo = hoje.minusYears(120);
        if (data.isBefore(limiteMaximo)) {
            return false;
        }

        return true;
    }
}