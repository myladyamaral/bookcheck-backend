package br.com.bookcheck.controller.dto.request;

import br.com.bookcheck.domain.enums.TipoUsuarioEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLeitorRequestDto extends UsuarioResquestDto  {
    private String nome;
    private String cpf;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataNascimento;


}
