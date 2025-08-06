package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.Sebo.CatalogoRequestDto;
import br.com.bookcheck.controller.dto.response.Sebo.CatalogoResponseDto;
import br.com.bookcheck.service.OpenLibraryService;
import br.com.bookcheck.service.Sebo.CatalogoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Gestão de Catálogo", description = "API para gerenciamento de catálogos de livros dos sebos")
public class CatalogoController {

    private final CatalogoService catalogoService;
    private final OpenLibraryService  openLibraryService;

    /**
     * Endpoint para adicionar um novo livro ao catálogo de um sebo
     * @param request DTO contendo os dados do livro a ser adicionado ao catálogo
     * @return ResponseEntity contendo o DTO de resposta com os dados do livro no catálogo
     */
    @Operation(summary = "Adicionar livro ao catálogo", description = "Adiciona um novo livro ao catálogo de um sebo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro adicionado ao catálogo com sucesso",
                    content = @Content(schema = @Schema(implementation = CatalogoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Sebo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/sebo")
    public ResponseEntity<CatalogoResponseDto> addLivroCatalogo(
            @Parameter(description = "Dados do livro a ser adicionado ao catálogo", required = true)
            @RequestBody @Valid CatalogoRequestDto request) {
        CatalogoResponseDto response = catalogoService.createCatalogo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para obter a lista paginada de livros no catálogo de um sebo
     * @param seboId ID do sebo
     * @param pageable Configuração de paginação
     * @return ResponseEntity contendo a página de livros do catálogo
     */
    @Operation(summary = "Listar livros do catálogo (paginado)", description = "Obtém a lista paginada de livros no catálogo de um sebo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de livros do catálogo retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "404", description = "Sebo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/books/{seboId}")
    public ResponseEntity<Page<CatalogoResponseDto>> getLivrosCatalogoBySeboId(
            @Parameter(description = "ID do sebo", required = true, example = "1")
            @PathVariable Long seboId,
            @Parameter(description = "Parâmetros de paginação (page, size, sort)")
            Pageable pageable) {
        return ResponseEntity.ok(catalogoService.getAllCatalogos(seboId, pageable));
    }

    /**
     * Endpoint para obter um livro específico do catálogo por ID
     * @param id ID do livro no catálogo
     * @return ResponseEntity contendo os dados do livro no catálogo
     */
    @Operation(summary = "Obter livro do catálogo por ID", description = "Obtém um livro específico do catálogo pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro do catálogo encontrado",
                    content = @Content(schema = @Schema(implementation = CatalogoResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado no catálogo"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CatalogoResponseDto> getLivroCatalogoById(
            @Parameter(description = "ID do livro no catálogo", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(catalogoService.getCatalogoById(id));
    }

    /**
     * Endpoint para obter a lista completa de livros no catálogo de um sebo
     * @param seboId ID do sebo
     * @return ResponseEntity contendo a lista completa de livros do catálogo
     */
    @Operation(summary = "Listar todos os livros do catálogo", description = "Obtém a lista completa de livros no catálogo de um sebo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de livros do catálogo retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = CatalogoResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Sebo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/list/{seboId}")
    public ResponseEntity<List<CatalogoResponseDto>> getLivrosCatalogoBySeboId(
            @Parameter(description = "ID do sebo", required = true, example = "1")
            @PathVariable Long seboId) {
        return ResponseEntity.ok(catalogoService.getAllCatalogos(seboId));
    }

    /**
     * Endpoint para remover um livro do catálogo de um sebo
     * @param id ID do livro a ser removido do catálogo
     * @return ResponseEntity vazio com status 204 (No Content)
     */
    @Operation(summary = "Remover livro do catálogo", description = "Remove um livro do catálogo de um sebo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro removido do catálogo com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado no catálogo"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/sebo/{id}")
    public ResponseEntity<Void> deleteLivroCatalogo(
            @Parameter(description = "ID do livro no catálogo a ser removido", required = true, example = "1")
            @PathVariable Long id) {
        catalogoService.deleteCatalogo(id);
        return ResponseEntity.noContent().build();
    }

}