package br.com.bookcheck.controller.dto.request.Leitor;

import br.com.bookcheck.domain.enums.EstadoConservacaoEnum;
import br.com.bookcheck.domain.enums.StatusLeituraEnum;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BibliotecaRequestDto {
    private Long leitorId;
    private String isbn;
    private EstadoConservacaoEnum estadoConservacao;
    private StatusLeituraEnum statusLeitura;
}

