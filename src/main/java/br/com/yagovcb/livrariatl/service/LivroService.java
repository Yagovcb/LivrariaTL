package br.com.yagovcb.livrariatl.service;


import br.com.yagovcb.livrariatl.dto.RequisicaoNovoLivro;
import br.com.yagovcb.livrariatl.model.Livro;
import br.com.yagovcb.livrariatl.model.Usuario;
import br.com.yagovcb.livrariatl.repository.LivroRepository;
import br.com.yagovcb.livrariatl.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Livro registrar(RequisicaoNovoLivro requisicaoNovoLivro) throws Exception {
        if (livroExiste(requisicaoNovoLivro.getNomeLivro())){
            throw new Exception("ja existe um livro cadastrado com esse nome " + requisicaoNovoLivro.getNomeLivro());
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usr = usuarioRepository.findByUsrName(username);
        Livro livro = requisicaoNovoLivro.toLivro();
        livro.setUsuario(usr);
        livroRepository.save(livro);

        return livro;
    }

    public Livro editar(long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usr = usuarioRepository.findByUsrName(username);
        Livro livro = buscaLivro(id);

        if (livro.getUsuario().getUsrName().equals(usr.getUsrName())){
            livro.setDataCadastro(LocalDate.now());
        } else{
            new Exception("Você só pode editar o livro que você cadastrou");
        }

        livroRepository.save(livro);

        return livro;
    }

    public Livro buscaLivro(long id) {
        return livroRepository.findById(id);
    }

    private boolean livroExiste(String nome){
        return Objects.nonNull(livroRepository.findByNomeLivro(nome));
    }

    public void delete(long id) throws Exception{
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usr = usuarioRepository.findByUsrName(username);
        Livro livro = livroRepository.findById(id);
        if (Objects.nonNull(livro)){
            if (livro.getUsuario().getUsrName().equals(usr.getUsrName()) ){
                livroRepository.deleteLivroById(id);
            }else{
                new Exception("Você só Deletar um livro que você cadastrou");
            }

        } else {
            new Exception("Livro inexistente");
        }


    }
}
