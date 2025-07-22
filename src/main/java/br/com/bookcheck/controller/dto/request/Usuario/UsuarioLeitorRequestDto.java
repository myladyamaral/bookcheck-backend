package br.com.bookcheck.controller.dto.request.Usuario;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLeitorRequestDto extends UsuarioResquestDto  {

    @NotBlank(message = "O CPF não pode estar vazio.")
    private String cpf;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataNascimento;


}
