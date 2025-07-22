package br.com.bookcheck.domain.entity.Usuario;

import br.com.bookcheck.domain.enums.TipoUsuarioEnum;
import br.com.bookcheck.domain.enums.converter.TipoUsuarioConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a system user, including authentication information,
 * account status, profile assignments, and association to a person and a unity.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    /**
     * Primary key of the user, generated via sequence.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String senha;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco endereco;

    @Column(length = 13)
    private String telefone;

    @Column(length = 300)
    private String descricao;

    @Column(name = "tipo_usuario", updatable = false, nullable = false)
    @Convert(converter = TipoUsuarioConverter.class)
    private TipoUsuarioEnum tipoUsuario;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Publicacao> publicacoes = new ArrayList<>();

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Comentario> comentarios = new ArrayList<>();

}
