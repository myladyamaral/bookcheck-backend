package br.com.bookcheck.controller.dto.response.Search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorSearchDto {
    private String id;
    private String nome;
}