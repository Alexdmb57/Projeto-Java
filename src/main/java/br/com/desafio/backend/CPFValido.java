package br.com.desafio.backend;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

// Define onde a anotação pode ser usada (apenas em campos)
@Target({ ElementType.FIELD })
// Define que a anotação estará disponível em tempo de execução
@Retention(RetentionPolicy.RUNTIME)
// Infomra ao Spring que esta é uma anotação de validação
@Constraint(validatedBy = CPFValidator.class)
@Documented
public @interface CPFValido {
  String message() default "CPF inválido ou em formato incorreto.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
