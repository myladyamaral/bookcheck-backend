package br.com.bookcheck.controller.dto.request.Usuario;

import lombok.Data;

@Data
public class EnviarMensagemRequestDto {
    private Long remetenteId;
    private Long destinatarioId;
    private String conteudo;
}