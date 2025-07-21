package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.UsuarioLeitorRequestDto;
import br.com.bookcheck.controller.dto.request.UsuarioSeboRequestDto;
import br.com.bookcheck.controller.dto.response.UsuarioLeitorResponseDto;
import br.com.bookcheck.controller.dto.response.UsuarioSeboResponseDto;
import br.com.bookcheck.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;


    // CRUD para UsuarioLeitor
    @PostMapping("/leitor")
    public ResponseEntity<UsuarioLeitorResponseDto> createLeitor(
            @RequestBody @Valid UsuarioLeitorRequestDto request,
            UriComponentsBuilder uriBuilder) {
        UsuarioLeitorResponseDto response = usuarioService.createLeitor(request);
        URI uri = uriBuilder.path("/usuario/leitor/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/leitor/{id}")
    public ResponseEntity<UsuarioLeitorResponseDto> getLeitorById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getLeitorById(id));
    }

    @GetMapping("/leitor")
    public ResponseEntity<List<UsuarioLeitorResponseDto>> getAllLeitores() {
        return ResponseEntity.ok(usuarioService.getAllLeitores());
    }

    // CRUD para UsuarioSebo
    @PostMapping("/sebo")
    public ResponseEntity<UsuarioSeboResponseDto> createSebo(
            @RequestBody @Valid UsuarioSeboRequestDto request,
            UriComponentsBuilder uriBuilder) {
        UsuarioSeboResponseDto response = usuarioService.createSebo(request);
        URI uri = uriBuilder.path("/usuario/sebo/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/sebo/{id}")
    public ResponseEntity<UsuarioSeboResponseDto> getSeboById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getSeboById(id));
    }

    @GetMapping("/sebo")
    public ResponseEntity<List<UsuarioSeboResponseDto>> getAllSebos() {
        return ResponseEntity.ok(usuarioService.getAllSebos());
    }

    // Endpoint genérico para deletar qualquer usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
