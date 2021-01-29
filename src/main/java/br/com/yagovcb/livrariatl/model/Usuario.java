package br.com.yagovcb.livrariatl.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NonNull
    @Column(name = "username", length = 50)
    private String usrName;

    @NonNull
    @Column(name = "password", length = 200)
    private String password;

    @NonNull
    @Column(name = "enabled")
    private Boolean habilitado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Livro> livros;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(
            name = "usuario_id", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "nomeRole")
    )
    private List<Role> roles;

}
