package br.com.bookcheck.controller.dto.response.Usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora;

    private UsuarioResponseDto autor;
    private List<ComentarioResponseDto> comentarios;
    private List<CurtidaResponseDto> curtidas; // ADICIONE ESTE CAMPO
}