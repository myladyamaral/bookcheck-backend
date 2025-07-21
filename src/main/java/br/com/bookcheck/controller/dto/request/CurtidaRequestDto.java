package br.com.bookcheck.controller.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurtidaRequestDto {
    private LocalDateTime dataHora = LocalDateTime.now();
    private Long usuarioId;
    private Long publicacaoId;
}

