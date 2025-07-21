package br.com.bookcheck.controller.dto.request;

import br.com.bookcheck.domain.enums.TipoUsuarioEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioSeboRequestDto extends UsuarioResquestDto {

    private String nomeFantasia;
    private String cnpj;
    private String horario;

}
