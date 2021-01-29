package br.com.yagovcb.livrariatl.dto;

import br.com.yagovcb.livrariatl.model.Livro;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class RequisicaoNovoLivro {

    private Long id;

    @NotBlank
    private String nomeLivro;

    @NotBlank
    private String autorLivro;


    public Livro toLivro() {
        Livro livro = new Livro();
        livro.setNomeLivro(nomeLivro);
        livro.setAutorLivro(autorLivro);
        livro.setDataCadastro(LocalDate.now());
        return livro;
    }
}
