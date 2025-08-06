package br.com.bookcheck.controller.dto.request.Leitor;

import br.com.bookcheck.domain.enums.EstadoConservacaoEnum;
import br.com.bookcheck.domain.enums.StatusLeituraEnum;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BibliotecaUpdateRequestDto {
    private EstadoConservacaoEnum estadoConservacao;
    private StatusLeituraEnum statusLeitura;
}

