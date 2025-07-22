package br.com.bookcheck.domain.entity.Leitor;

import br.com.bookcheck.domain.entity.Livro.Livro;
import br.com.bookcheck.domain.entity.Usuario.UsuarioLeitor;
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
@Table(name = "livro_desejado")
public class LivroDesejado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "livro_desejado_seq")
    @SequenceGenerator(name = "livro_desejado_seq", sequenceName = "livro_desejado_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leitor_id", nullable = false)
    private UsuarioLeitor leitor;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

}
