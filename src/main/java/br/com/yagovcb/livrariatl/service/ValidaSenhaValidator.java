package br.com.yagovcb.livrariatl.service;

import br.com.yagovcb.livrariatl.dto.RequisicaoNovoUsuario;
import br.com.yagovcb.livrariatl.repository.ValidaSenha;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidaSenhaValidator implements ConstraintValidator<ValidaSenha, Object> {
    @Override
    public void initialize(ValidaSenha constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        RequisicaoNovoUsuario usuario = (RequisicaoNovoUsuario)value;
        return usuario.getPassword().equals(usuario.getPasswordRetype());
    }
}
