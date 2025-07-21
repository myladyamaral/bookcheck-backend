package br.com.bookcheck.controller.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroDesejadoRequestDto {
    private Long leitorId;
    private Long livroId;
}

