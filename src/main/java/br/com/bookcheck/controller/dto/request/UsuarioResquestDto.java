package br.com.bookcheck.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class UsuarioResquestDto {
    private Long id;
    private String email;
    private String senha;
    private EnderecoRequestDto endereco;
    private String cep;
    private String telefone;
    private String descricao;
}