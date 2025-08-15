package br.com.bookcheck.controller.dto.request.Leitor;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroDesejadoRequestDto {
    private Long leitorId;
    private String workId;
}

