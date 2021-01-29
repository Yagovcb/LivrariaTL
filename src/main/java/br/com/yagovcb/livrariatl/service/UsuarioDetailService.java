package br.com.yagovcb.livrariatl.service;

import br.com.yagovcb.livrariatl.model.Role;
import br.com.yagovcb.livrariatl.model.Usuario;
import br.com.yagovcb.livrariatl.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UsuarioDetailService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsrName(s);

        if (Objects.isNull(usuario)){
            throw new UsernameNotFoundException("Nenhum usuario encontrado: "+ s);
        }
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return new User(usuario.getUsrName(), usuario.getPassword(), usuario.getHabilitado(),
                accountNonExpired, credentialsNonExpired,accountNonLocked, getAuthorities(usuario.getRoles()));
    }
    private static List<GrantedAuthority> getAuthorities (List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getNomeRole())));

        return authorities;
    }

}
