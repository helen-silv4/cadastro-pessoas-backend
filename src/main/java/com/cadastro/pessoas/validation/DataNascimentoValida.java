package com.cadastro.pessoas.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DataNascimentoValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DataNascimentoValida {
    String message() default "Data de nascimento inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
