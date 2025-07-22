package br.com.bookcheck.controller.dto.request.Livro;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditoraRequestDto {
    private String nomeFantasia;
    private String cnpj;
}

