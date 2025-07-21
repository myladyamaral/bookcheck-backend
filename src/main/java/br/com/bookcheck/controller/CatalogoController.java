package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.CatalogoRequestDto;
import br.com.bookcheck.controller.dto.response.CatalogoResponseDto;
import br.com.bookcheck.service.CatalogoService;
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
@RequestMapping("api")
@RequiredArgsConstructor
public class CatalogoController {

    private final CatalogoService catalogoService;

    @PostMapping("/sebo/catalogo")
    public ResponseEntity<CatalogoResponseDto> createCatalogo(
            @RequestBody @Valid CatalogoRequestDto request) {
        CatalogoResponseDto response = catalogoService.createCatalogo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/catalogo/all/{seboId}")
    public ResponseEntity<Page<CatalogoResponseDto>> getAllCatalogosBySeboId(@PathVariable Long seboId, Pageable pageable) {
        return ResponseEntity.ok(catalogoService.getAllCatalogosPage(seboId, pageable));
    }

    @GetMapping("/catalogo/{id}")
    public ResponseEntity<CatalogoResponseDto> getCatalogoById(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(catalogoService.getCatalogoById(id));
    }

    @GetMapping("/catalogo/list/{id}")
    public ResponseEntity<List<CatalogoResponseDto>> getAllCatalogos(@PathVariable Long id) {
        return ResponseEntity.ok(catalogoService.getAllCatalogos(id));
    }

    @DeleteMapping("/sebo/catalogo/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        catalogoService.deleteCatalogo(id);
        return ResponseEntity.noContent().build();
    }
}
