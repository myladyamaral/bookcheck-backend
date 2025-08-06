package br.com.bookcheck.controller.dto.response.Leitor;

import br.com.bookcheck.controller.dto.response.Usuario.UsuarioLeitorResponseDto;
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
    private UsuarioLeitorResponseDto leitor;
    private String isbn;
    private EstadoConservacaoEnum estadoConservacao;
    private StatusLeituraEnum statusLeitura;
}

