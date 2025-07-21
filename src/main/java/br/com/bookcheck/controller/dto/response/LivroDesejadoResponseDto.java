package br.com.bookcheck.controller.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroDesejadoResponseDto {
    private Long id;
    private Long leitorId;
    private Long livroId;
}

