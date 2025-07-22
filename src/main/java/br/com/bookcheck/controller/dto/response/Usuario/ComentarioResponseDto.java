package br.com.bookcheck.controller.dto.response.Usuario;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioResponseDto {
    private Long id;
    private String texto;
    private LocalDateTime dataHora;
    private UsuarioResponseDto usuarioId;
    private PublicacaoResponseDto publicacaoId;
}

