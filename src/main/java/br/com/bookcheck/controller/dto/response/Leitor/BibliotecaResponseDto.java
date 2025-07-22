package br.com.bookcheck.controller.dto.response.Leitor;

import br.com.bookcheck.domain.enums.EstadoConservacaoEnum;
import br.com.bookcheck.domain.enums.StatusLeituraEnum;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BibliotecaResponseDto {
    private Long id;
    private Long leitorId;
    private Long livroId;
    private EstadoConservacaoEnum estadoConservacao;
    private StatusLeituraEnum statusLeitura;
}

