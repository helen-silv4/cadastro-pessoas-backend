// validation/CpfValidator.java
package com.cadastro.pessoas.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<CpfValido, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null) return true;

        // remove máscara antes de validar
        String digits = cpf.replaceAll("[^0-9]", "");

        if (digits.length() != 11) return false;

        // rejeita CPFs com todos os dígitos iguais
        if (digits.chars().distinct().count() == 1) return false;

        // valida primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) sum += (digits.charAt(i) - '0') * (10 - i);
        int first = 11 - (sum % 11);
        if (first >= 10) first = 0;
        if (first != (digits.charAt(9) - '0')) return false;

        // valida segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) sum += (digits.charAt(i) - '0') * (11 - i);
        int second = 11 - (sum % 11);
        if (second >= 10) second = 0;
        return second == (digits.charAt(10) - '0');
    }
}