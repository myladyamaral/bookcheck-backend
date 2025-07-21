package br.com.bookcheck.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
public class UsuarioSebo extends Usuario {

    @Column(length = 100, nullable = false)
    private String nomeFantasia;

    @Column(length = 14)
    private String cnpj;

    @Column(length = 300)
    private String horario;


}