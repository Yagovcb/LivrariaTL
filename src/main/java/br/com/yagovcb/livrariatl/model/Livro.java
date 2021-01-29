package br.com.yagovcb.livrariatl.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "nome_livro", length = 200)
    private String nomeLivro;

    @NonNull
    @Column(name = "autor_livro", length = 50)
    private String autorLivro;

    @NonNull
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
}
