package br.com.bookcheck.controller.dto.response.Search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultDto {
    private List<LivroSearchDto> livros;
    private List<AutorSearchDto> autores;
    private List<EditoraSearchDto> editoras;
}
