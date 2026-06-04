package com.cadastro.pessoas.exception;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFValidator implements ConstraintValidator<CPFValido, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {

        if (cpf == null || cpf.isBlank()) {
            return false;
        }

        // remove pontos e traço
        String cpfLimpo = cpf.replaceAll("[.\\-]", "");

        // deve ter exatamente 11 dígitos
        if (cpfLimpo.length() != 11) {
            return false;
        }

        // rejeita CPFs com todos os dígitos iguais (ex: 111.111.111-11)
        if (cpfLimpo.chars().distinct().count() == 1) {
            return false;
        }

        // valida primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpfLimpo.charAt(i)) * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) primeiroDigito = 0;

        if (primeiroDigito != Character.getNumericValue(cpfLimpo.charAt(9))) {
            return false;
        }

        // valida segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpfLimpo.charAt(i)) * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) segundoDigito = 0;

        return segundoDigito == Character.getNumericValue(cpfLimpo.charAt(10));
    }
}