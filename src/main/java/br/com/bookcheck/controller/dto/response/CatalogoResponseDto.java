package br.com.bookcheck.controller.dto.response;

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
    private Long seboId;
    private Long livroId;
    private EstadoConservacaoEnum estadoConservacao;
    private Double preco;
    private Integer quantidade;
    private DisponibilidadeCatalogoEnum status;
}

