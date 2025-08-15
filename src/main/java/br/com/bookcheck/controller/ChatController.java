package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.ConversaDTO;
import br.com.bookcheck.controller.dto.MensagemDTO;
import br.com.bookcheck.service.Chat.ChatService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/conversations")
    public List<ConversaDTO> listarConversas(@RequestParam Long usuarioId) {
        return chatService.listarConversas(usuarioId);
    }

    @GetMapping("/messages")
    public List<MensagemDTO> listarMensagens(@RequestParam Long currentUserId, @RequestParam Long userId) {
        return chatService.listarMensagens(currentUserId, userId);
    }

    @PostMapping("/enviar")
    public MensagemDTO enviarMensagem(@RequestBody EnviarMensagemRequest request) {
        return chatService.enviarMensagem(request.getRemetenteId(), request.getDestinatarioId(), request.getConteudo());
    }

    @Data
    public static class EnviarMensagemRequest {
        private Long remetenteId;
        private Long destinatarioId;
        private String conteudo;
    }
}
