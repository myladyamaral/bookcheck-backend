package br.com.bookcheck.controller.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UsuarioSeboResponseDto extends UsuarioResponseDto {
    private String nomeFantasia;
    private String cnpj;
    private String horario;
}