package br.com.bookcheck.controller.dto.response.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemResponseDto {
    private String texto;
    private LocalDateTime dataHora;
    private UsuarioResponseDto remetenteId;
    private UsuarioResponseDto destinatarioId;
}
