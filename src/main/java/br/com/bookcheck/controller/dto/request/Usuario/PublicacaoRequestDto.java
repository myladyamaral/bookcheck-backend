package br.com.bookcheck.controller.dto.request.Usuario;

import com.fasterxml.jackson.annotation.JsonFormat; // Importe esta classe
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublicacaoRequestDto {

    @NotBlank(message = "O texto da publicação não pode estar vazio.")
    private String texto;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora = LocalDateTime.now();
}