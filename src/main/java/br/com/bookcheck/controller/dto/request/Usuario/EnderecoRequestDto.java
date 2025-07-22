package br.com.bookcheck.controller.dto.request.Usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequestDto {

    @NotBlank(message = "CEP não pode estar vazio.")
    private String cep;

    @NotBlank(message = "Campo tipo logradouro não pode estar vazio.")
    private String tipoLogradouro;

    @NotBlank(message = "Campo logradouro não pode estar vazio.")
    private String logradouro;

    @NotBlank(message = "Campo numero não pode estar vazio.")
    private String numero;

    private String complemento;

    @NotBlank(message = "Campo bairro não pode estar vazio.")
    private String bairro;

    @NotBlank(message = "Campo cidade não pode estar vazio.")
    private String cidade;

    @NotBlank(message = "Campo UF não pode estar vazio.")
    private String uf;
}

