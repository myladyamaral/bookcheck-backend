package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.response.Usuario.PublicacaoResponseDto;
import br.com.bookcheck.service.Usuario.CurtidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/api/publicacao")
@RequiredArgsConstructor
public class CurtidaController {

    private final CurtidaService curtidaService;

    @PostMapping("/{id}/curtir")
    public ResponseEntity<PublicacaoResponseDto> curtirDescurtir(@PathVariable("id") Long publicacaoId, Principal principal) {
        PublicacaoResponseDto publicacaoAtualizada = curtidaService.curtirDescurtirPublicacao(publicacaoId, principal.getName());
        return ResponseEntity.ok(publicacaoAtualizada);
    }
}