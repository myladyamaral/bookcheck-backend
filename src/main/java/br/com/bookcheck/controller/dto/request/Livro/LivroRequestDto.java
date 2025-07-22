package br.com.bookcheck.controller.dto.request.Livro;

import br.com.bookcheck.domain.enums.GeneroLiterarioEnum;
import lombok.*;

import java.time.Year;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroRequestDto {
    private String isbn;
    private String titulo;
    private Year ano;
    private String idioma;
    private GeneroLiterarioEnum genero;
    private Long autorId;
    private Long editoraId;
}

