package br.com.bookcheck.controller.dto.request.Usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "O nome não pode estar vazio.")
    private String nome;

    @NotBlank(message = "O e-mail não pode estar vazio.")
    @Email
    private String email;

    @NotBlank(message = "A senha não pode estar vazia.")
    private String senha;

    @NotNull
    @Valid
    private EnderecoRequestDto endereco;

    private String telefone;

    private String descricao;
}