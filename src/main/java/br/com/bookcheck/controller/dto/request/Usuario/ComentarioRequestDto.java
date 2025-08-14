package br.com.bookcheck.controller.dto.request.Usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioRequestDto {

    @NotBlank(message = "O texto do comentário não pode estar vazio.")
    private String texto;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora = LocalDateTime.now();

    @NotNull(message = "O ID da publicação não pode ser nulo.")
    private Long publicacaoId;
}