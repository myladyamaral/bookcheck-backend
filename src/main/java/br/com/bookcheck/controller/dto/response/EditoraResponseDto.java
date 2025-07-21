package br.com.bookcheck.controller.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditoraResponseDto {
    private Long id;
    private String nome;
    private String cnpj;
}

