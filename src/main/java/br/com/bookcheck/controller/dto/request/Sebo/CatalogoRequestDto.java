package br.com.bookcheck.controller.dto.request.Sebo;

import br.com.bookcheck.domain.enums.DisponibilidadeCatalogoEnum;
import br.com.bookcheck.domain.enums.EstadoConservacaoEnum;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoRequestDto {
    private Long seboId;
    private Long livroId;
    private EstadoConservacaoEnum estadoConservacao;
    private Double preco;
    private Integer quantidade;
    private DisponibilidadeCatalogoEnum status;
}

