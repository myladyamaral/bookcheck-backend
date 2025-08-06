package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.Leitor.LivroDesejadoRequestDto;
import br.com.bookcheck.controller.dto.response.Leitor.LivroDesejadoResponseDto;
import br.com.bookcheck.service.Leitor.LivroDesejadoService;
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
@RequestMapping("api/leitor/livroDesejado")
@RequiredArgsConstructor
@Tag(name = "Gestão de Livros Desejados", description = "API para gerenciamento de livros desejados pelos leitores")
public class LivroDesejadoController {

    private final LivroDesejadoService livroDesejadoService;

    /**
     * Endpoint para adicionar um novo livro à lista de desejos de um leitor
     * @param request DTO contendo os dados do livro desejado
     * @return ResponseEntity contendo o DTO de resposta com os dados do livro desejado criado
     */
    @Operation(summary = "Adicionar livro desejado", description = "Adiciona um novo livro à lista de desejos de um leitor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro desejado adicionado com sucesso",
                    content = @Content(schema = @Schema(implementation = LivroDesejadoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Leitor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping()
    public ResponseEntity<LivroDesejadoResponseDto> addLivroLivroDesejado(
            @Parameter(description = "Dados do livro desejado a ser adicionado", required = true)
            @RequestBody @Valid LivroDesejadoRequestDto request) {
        LivroDesejadoResponseDto response = livroDesejadoService.createLivroDesejado(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para obter a lista paginada de livros desejados de um leitor
     * @param leitorId ID do leitor
     * @param pageable Configuração de paginação
     * @return ResponseEntity contendo a página de livros desejados
     */
    @Operation(summary = "Listar livros desejados (paginado)", description = "Obtém a lista paginada de livros desejados de um leitor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de livros desejados retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "404", description = "Leitor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/all/{leitorId}")
    public ResponseEntity<Page<LivroDesejadoResponseDto>> getListaDesejos(
            @Parameter(description = "ID do leitor", required = true, example = "1")
            @PathVariable Long leitorId,
            @Parameter(description = "Parâmetros de paginação (page, size, sort)")
            Pageable pageable) {
        return ResponseEntity.ok(livroDesejadoService.getAllLivrosDesejados(leitorId, pageable));
    }

    /**
     * Endpoint para obter um livro desejado específico por ID
     * @param id ID do livro desejado
     * @return ResponseEntity contendo os dados do livro desejado
     */
    @Operation(summary = "Obter livro desejado por ID", description = "Obtém um livro desejado específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro desejado encontrado",
                    content = @Content(schema = @Schema(implementation = LivroDesejadoResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Livro desejado não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LivroDesejadoResponseDto> getLivroDesejadoById(
            @Parameter(description = "ID do livro desejado", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(livroDesejadoService.getLivroDesejadoById(id));
    }

    /**
     * Endpoint para obter a lista completa de livros desejados de um leitor
     * @param id ID do leitor
     * @return ResponseEntity contendo a lista de livros desejados
     */
    @Operation(summary = "Listar todos os livros desejados", description = "Obtém a lista completa de livros desejados de um leitor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de livros desejados retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = LivroDesejadoResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Leitor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/list/{id}")
    public ResponseEntity<List<LivroDesejadoResponseDto>> getListaDesejos(
            @Parameter(description = "ID do leitor", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(livroDesejadoService.getAllLivrosDesejados(id));
    }

    /**
     * Endpoint para remover um livro da lista de desejos
     * @param id ID do livro desejado a ser removido
     * @return ResponseEntity vazio com status 204 (No Content)
     */
    @Operation(summary = "Remover livro desejado", description = "Remove um livro da lista de desejos de um leitor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro desejado removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro desejado não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeLivroDesejado(
            @Parameter(description = "ID do livro desejado a ser removido", required = true, example = "1")
            @PathVariable Long id) {
        livroDesejadoService.deleteLivroDesejado(id);
        return ResponseEntity.noContent().build();
    }
}