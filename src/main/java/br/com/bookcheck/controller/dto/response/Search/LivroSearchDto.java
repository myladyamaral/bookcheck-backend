package br.com.bookcheck.controller.dto.response.Search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroSearchDto {
    private String id;
    private String titulo;
    private String autor;
}