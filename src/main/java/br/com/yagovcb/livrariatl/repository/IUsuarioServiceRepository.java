package br.com.yagovcb.livrariatl.repository;


import br.com.yagovcb.livrariatl.dto.RequisicaoNovoUsuario;
import br.com.yagovcb.livrariatl.model.Usuario;

public interface IUsuarioServiceRepository {

    Usuario registro(RequisicaoNovoUsuario userDto) throws Exception;
}
