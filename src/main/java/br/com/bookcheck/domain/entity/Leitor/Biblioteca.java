package br.com.bookcheck.domain.entity.Leitor;

import br.com.bookcheck.domain.entity.Livro.Livro;
import br.com.bookcheck.domain.entity.Usuario.UsuarioLeitor;
import br.com.bookcheck.domain.enums.EstadoConservacaoEnum;
import br.com.bookcheck.domain.enums.StatusLeituraEnum;
import br.com.bookcheck.domain.enums.converter.EstadoConservacaoConverter;
import br.com.bookcheck.domain.enums.converter.StatusLeituraConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "biblioteca")
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "biblioteca_seq")
    @SequenceGenerator(name = "biblioteca_seq", sequenceName = "biblioteca_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leitor_id", nullable = false)
    private UsuarioLeitor leitor;

    @Column(nullable = false)
    private String isbn;

    @Column(name = "estado_conservacao", nullable = false)
    @Convert(converter = EstadoConservacaoConverter.class)
    private EstadoConservacaoEnum estadoConservacao;

    @Column(name = "status_leitura", nullable = false)
    @Convert(converter = StatusLeituraConverter.class)
    private StatusLeituraEnum statusLeitura;

}
