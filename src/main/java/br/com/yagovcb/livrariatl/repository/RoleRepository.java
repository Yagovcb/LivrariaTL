package br.com.yagovcb.livrariatl.repository;

import br.com.yagovcb.livrariatl.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByNomeRole(String nome);

}
