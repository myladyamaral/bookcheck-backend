package br.com.bookcheck.controller.dto.request.Usuario;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioRequestDto {
    private String texto;
    private LocalDateTime dataHora = LocalDateTime.now();
    private Long usuarioId;
    private Long publicacaoId;
}

