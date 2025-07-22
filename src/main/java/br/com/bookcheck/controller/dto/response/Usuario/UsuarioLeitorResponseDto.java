package br.com.bookcheck.controller.dto.response.Usuario;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class UsuarioLeitorResponseDto extends UsuarioResponseDto {
    private String cpf;
    private LocalDateTime dataNascimento;
}