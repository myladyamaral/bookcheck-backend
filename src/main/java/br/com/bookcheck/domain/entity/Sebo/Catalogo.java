package br.com.bookcheck.domain.entity.Sebo;

import br.com.bookcheck.domain.entity.Livro.Livro;
import br.com.bookcheck.domain.entity.UsuarioSebo;
import br.com.bookcheck.domain.enums.DisponibilidadeCatalogoEnum;
import br.com.bookcheck.domain.enums.EstadoConservacaoEnum;
import br.com.bookcheck.domain.enums.converter.DisponibilidadeCatalogoConverter;
import br.com.bookcheck.domain.enums.converter.EstadoConservacaoConverter;
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
@Table(name = "catalogo")
public class Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalogo_seq")
    @SequenceGenerator(name = "catalogo_seq", sequenceName = "catalogo_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sebo_id", nullable = false)
    private UsuarioSebo sebo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @Column(name = "estado_conservacao", nullable = false)
    @Convert(converter = EstadoConservacaoConverter.class)
    private EstadoConservacaoEnum estadoConservacao;

    private Double preco;

    private Integer quantidade;

    @Column(name = "status", nullable = false)
    @Convert(converter = DisponibilidadeCatalogoConverter.class)
    private DisponibilidadeCatalogoEnum status;


}
