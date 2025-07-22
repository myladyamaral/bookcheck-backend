package br.com.bookcheck.domain.entity.Usuario;

import br.com.bookcheck.domain.entity.Leitor.LivroDesejado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a leitor in the system. This entity stores various leitoral and organizational
 * details, including name, contact, type (individual or legal entity), position, and status.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@PrimaryKeyJoinColumn(name = "usuario_id")
public class UsuarioLeitor extends Usuario {


    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(name = "data_nascimento")
    private LocalDateTime dataNascimento;

    @OneToMany(mappedBy = "leitor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LivroDesejado> livrosDesejados = new ArrayList<>();

}