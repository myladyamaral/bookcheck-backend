package br.com.bookcheck.controller.dto.response;

import br.com.bookcheck.controller.dto.response.Usuario.UsuarioResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String token;
    private String refreshToken;
    private UsuarioResponseDto user;
}