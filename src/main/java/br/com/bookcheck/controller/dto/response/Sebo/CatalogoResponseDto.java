package br.com.bookcheck.controller.dto.response.Sebo;

import br.com.bookcheck.controller.dto.response.Livro.LivroResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioSeboResponseDto;
import br.com.bookcheck.domain.enums.DisponibilidadeCatalogoEnum;
import br.com.bookcheck.domain.enums.EstadoConservacaoEnum;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoResponseDto {
    private Long id;
    private UsuarioSeboResponseDto sebo;
    private LivroResponseDto livro;
    private EstadoConservacaoEnum estadoConservacao;
    private Double preco;
    private Integer quantidade;
    private DisponibilidadeCatalogoEnum status;
}

