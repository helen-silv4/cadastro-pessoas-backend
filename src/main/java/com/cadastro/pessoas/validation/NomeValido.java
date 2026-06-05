package com.cadastro.pessoas.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NomeValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NomeValido {
    String message() default "Nome inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
