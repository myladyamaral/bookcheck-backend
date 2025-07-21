package br.com.bookcheck.domain.entity;

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
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_seq")
    @SequenceGenerator(name = "endereco_seq", sequenceName = "endereco_seq", allocationSize = 1)
    private Long id;

    @Column(length = 10, nullable = false)
    private String cep;

    @Column(length = 30,name = "tipo_logradouro", nullable = false)
    private String tipoLogradouro;

    @Column(length = 100, nullable = false)
    private String logradouro;

    @Column(length = 45, nullable = false)
    private String numero;

    @Column(length = 100)
    private String complemento;

    @Column(length = 100, nullable = false)
    private String bairro;

    @Column(length = 150, nullable = false)
    private String cidade;

    @Column(length = 10, nullable = false)
    private String uf;
}
