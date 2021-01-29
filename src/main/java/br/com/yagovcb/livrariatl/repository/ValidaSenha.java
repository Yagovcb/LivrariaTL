package br.com.yagovcb.livrariatl.repository;

import br.com.yagovcb.livrariatl.service.ValidaSenhaValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE,ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidaSenhaValidator.class)
@Documented
public @interface ValidaSenha {
    String message() default "Senhas não conferem";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
