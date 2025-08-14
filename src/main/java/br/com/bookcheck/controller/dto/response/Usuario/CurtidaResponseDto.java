package br.com.bookcheck.controller.dto.response.Usuario;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurtidaResponseDto {
    private Long id;
    private LocalDateTime dataHora;
    private UsuarioResponseDto autor;
}