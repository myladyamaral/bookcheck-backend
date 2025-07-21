package br.com.bookcheck.controller.dto.response;

import br.com.bookcheck.domain.enums.GeneroLiterarioEnum;
import lombok.*;

import java.time.Year;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponseDto {
    private Long id;
    private String isbn;
    private String titulo;
    private Year ano;
    private String idioma;
    private GeneroLiterarioEnum genero;
    private AutorResponseDto autor;
    private EditoraResponseDto editora;
}

