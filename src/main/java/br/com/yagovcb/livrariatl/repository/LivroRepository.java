package br.com.yagovcb.livrariatl.repository;

import br.com.yagovcb.livrariatl.model.Livro;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("select l from Livro l join l.usuario u where u.usrName = :username" )
    List<Livro> findAllByUsuario(@Param("username") String username);

    Livro findByNomeLivro(String nome);

    Livro findById(long id);

    void deleteLivroById(long id);
}
