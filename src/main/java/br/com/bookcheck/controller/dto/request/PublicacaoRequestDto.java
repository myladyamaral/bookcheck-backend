package br.com.bookcheck.controller.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublicacaoRequestDto {
    private String texto;
    private Long usuarioId;
    private LocalDateTime dataHora = LocalDateTime.now();
}

