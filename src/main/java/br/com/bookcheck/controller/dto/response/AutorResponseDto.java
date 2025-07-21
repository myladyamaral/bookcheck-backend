package br.com.bookcheck.controller.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutorResponseDto {
    private Long id;
    private String nome;
}

