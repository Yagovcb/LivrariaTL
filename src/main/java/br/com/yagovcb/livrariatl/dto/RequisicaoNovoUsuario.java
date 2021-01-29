package br.com.yagovcb.livrariatl.dto;

import br.com.yagovcb.livrariatl.model.Usuario;
import br.com.yagovcb.livrariatl.repository.ValidaSenha;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ValidaSenha
public class RequisicaoNovoUsuario {

    @NotEmpty
    @NotBlank
    private String usrName;
    @NotBlank
    @NotEmpty
    private String password;
    @NotBlank
    @NotEmpty
    private String passwordRetype;
    private Boolean habilitado;

    public Usuario toUsuario() {
        Usuario usuario = new Usuario();
        usuario.setHabilitado(true);
        usuario.setUsrName(usrName);
        usuario.setPassword(encoder().encode(password));
        return usuario;
    }

    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
