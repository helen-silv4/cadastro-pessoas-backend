package com.cadastro.pessoas.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NomeValidator implements ConstraintValidator<NomeValido, String> {

    @Override
    public boolean isValid(String nome, ConstraintValidatorContext context) {
        if (nome == null) return true; // @NotBlank cuida disso

        String semEspacos = nome.replaceAll(" ", "");

        if (!nome.matches("^[a-zA-Z]+( [a-zA-Z]+)+$")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "Nome deve conter pelo menos duas palavras, apenas letras e espaços, sem acentos ou caracteres especiais")
                .addConstraintViolation();
            return false;
        }

        if (semEspacos.length() < 7) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "Nome completo deve ter no mínimo 7 letras no total")
                .addConstraintViolation();
            return false;
        }

        return true;
    }
}