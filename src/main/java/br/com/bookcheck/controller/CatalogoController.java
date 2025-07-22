package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.Sebo.CatalogoRequestDto;
import br.com.bookcheck.controller.dto.response.Sebo.CatalogoResponseDto;
import br.com.bookcheck.service.Sebo.CatalogoService;
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
@RequestMapping("api/catalogo")
@RequiredArgsConstructor
public class CatalogoController {

    private final CatalogoService catalogoService;


    @PostMapping("/sebo")
    public ResponseEntity<CatalogoResponseDto> addLivroCatalogo(
            @RequestBody @Valid CatalogoRequestDto request) {
        CatalogoResponseDto response = catalogoService.createCatalogo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/all/{seboId}")
    public ResponseEntity<Page<CatalogoResponseDto>> getLivrosCatalogoBySeboId(@PathVariable Long seboId, Pageable pageable) {
        return ResponseEntity.ok(catalogoService.getAllCatalogos(seboId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogoResponseDto> getLivroCatalogoById(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(catalogoService.getCatalogoById(id));
    }

    @GetMapping("/list/{seboId}")
    public ResponseEntity<List<CatalogoResponseDto>> getLivrosCatalogoBySeboId(@PathVariable Long seboId) {
        return ResponseEntity.ok(catalogoService.getAllCatalogos(seboId));
    }


    @DeleteMapping("/sebo/{id}")
    public ResponseEntity<Void> deleteLivroCatalogo(@PathVariable Long id) {
        catalogoService.deleteCatalogo(id);
        return ResponseEntity.noContent().build();
    }
}
