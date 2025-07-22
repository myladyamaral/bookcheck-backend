package br.com.bookcheck.controller.dto.response.Leitor;

import br.com.bookcheck.controller.dto.response.Livro.LivroResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioLeitorResponseDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroDesejadoResponseDto {
    private Long id;
    private UsuarioLeitorResponseDto leitor;
    private LivroResponseDto livro;
}

