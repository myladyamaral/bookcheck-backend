package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.Leitor.LivroDesejadoRequestDto;
import br.com.bookcheck.controller.dto.response.Leitor.LivroDesejadoResponseDto;
import br.com.bookcheck.service.Leitor.LivroDesejadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/leitor/livroDesejado")
@RequiredArgsConstructor
public class LivroDesejadoController {

    private final LivroDesejadoService livroDesejadoService;

    @PostMapping()
    public ResponseEntity<LivroDesejadoResponseDto> addLivroLivroDesejado(
            @RequestBody @Valid LivroDesejadoRequestDto request) {
        LivroDesejadoResponseDto response = livroDesejadoService.createLivroDesejado(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/all/{leitorId}")
    public ResponseEntity<Page<LivroDesejadoResponseDto>> getListaDesejos(@PathVariable Long leitorId, Pageable pageable) {
        return ResponseEntity.ok(livroDesejadoService.getAllLivrosDesejados(leitorId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDesejadoResponseDto> getLivroDesejadoById(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(livroDesejadoService.getLivroDesejadoById(id));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<LivroDesejadoResponseDto>> getListaDesejos(@PathVariable Long id) {
        return ResponseEntity.ok(livroDesejadoService.getAllLivrosDesejados(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeLivroDesejado(@PathVariable Long id) {
        livroDesejadoService.deleteLivroDesejado(id);
        return ResponseEntity.noContent().build();
    }
}
