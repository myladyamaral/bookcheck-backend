package br.com.bookcheck.controller.dto.response.Usuario;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublicacaoResponseDto {
    private Long id;
    private String texto;
    private LocalDateTime dataHora;
    private UsuarioResponseDto autorId;
    private List<ComentarioResponseDto> comentarios;
}

