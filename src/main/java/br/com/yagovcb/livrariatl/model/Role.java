package br.com.yagovcb.livrariatl.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Role implements GrantedAuthority {

    @Id
    private String nomeRole;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    @Override
    public String getAuthority() {
        return this.nomeRole;
    }
}
