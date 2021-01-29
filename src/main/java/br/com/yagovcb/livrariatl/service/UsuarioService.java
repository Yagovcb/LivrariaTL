package br.com.yagovcb.livrariatl.service;

import br.com.yagovcb.livrariatl.dto.RequisicaoNovoUsuario;
import br.com.yagovcb.livrariatl.model.Role;
import br.com.yagovcb.livrariatl.model.Usuario;
import br.com.yagovcb.livrariatl.repository.IUsuarioServiceRepository;
import br.com.yagovcb.livrariatl.repository.RoleRepository;
import br.com.yagovcb.livrariatl.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Objects;

@Service
public class UsuarioService implements IUsuarioServiceRepository {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public Usuario registro(RequisicaoNovoUsuario userDto) throws UsernameNotFoundException {
        Role role = roleRepository.findByNomeRole("ROLE_USUARIO");
        if (usuarioExiste(userDto.getUsrName())){
            throw new UsernameNotFoundException("Ja existe um usuario cadastrado com esse login" + userDto.getUsrName());
        }
        Usuario usuario = userDto.toUsuario();
        usuario.setRoles(Arrays.asList(role));
        usuarioRepository.save(usuario);

        return usuario;
    }

    private boolean usuarioExiste(String username){
        return Objects.nonNull(usuarioRepository.findByUsrName(username));
    }
}
