package br.com.yagovcb.livrariatl.repository;

import br.com.yagovcb.livrariatl.model.Role;
import br.com.yagovcb.livrariatl.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Usuario findByUsrName(String username);

    @Query("select r from Usuario u left join u.roles r where u.usrName = :username" )
    Role findRolesByUsuario(@Param("username") String username);
}
