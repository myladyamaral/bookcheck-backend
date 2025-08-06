package br.com.bookcheck.domain.entity.Livro;

import br.com.bookcheck.domain.enums.GeneroLiterarioEnum;
import br.com.bookcheck.domain.enums.converter.EstadoConservacaoConverter;
import br.com.bookcheck.domain.enums.converter.GeneroLiterarioConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Year;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "livro_seq")
    @SequenceGenerator(name = "livro_seq", sequenceName = "livro_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Year ano;

    @Column(nullable = false)
    private String idioma;
/*
    @Column(name = "genero", nullable = false)
    @Convert(converter = GeneroLiterarioConverter.class)
    private GeneroLiterarioEnum genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editora_id", nullable = false)
    private Editora editora;*/

}
