package br.com.bookcheck.controller.dto.response.Usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora;

    private UsuarioResponseDto autor;
    private PublicacaoResponseDto publicacao;
}