package br.com.bookcheck.controller.dto.request.Usuario;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioSeboRequestDto extends UsuarioResquestDto {

    private String cnpj;

    private String horario;

}
