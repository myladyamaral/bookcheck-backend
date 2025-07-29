package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.Leitor.BibliotecaRequestDto;
import br.com.bookcheck.controller.dto.response.GenericSuccessResponseDto;
import br.com.bookcheck.controller.dto.response.Leitor.BibliotecaResponseDto;
import br.com.bookcheck.service.Leitor.BibliotecaService;
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
@RequestMapping("api/leitor/biblioteca")
@RequiredArgsConstructor
@Tag(name = "Gestão de Biblioteca do Leitor", description = "API para gerenciamento da biblioteca pessoal dos leitores")
public class BibliotecaController {

    private final BibliotecaService bibliotecaService;

    /**
     * Endpoint para adicionar um novo livro à biblioteca do leitor
     * @param request DTO contendo os dados do livro a ser adicionado
     * @return ResponseEntity com os dados do livro adicionado
     */
    @Operation(summary = "Adicionar livro à biblioteca", description = "Adiciona um novo livro à biblioteca pessoal do leitor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro adicionado à biblioteca com sucesso",
                    content = @Content(schema = @Schema(implementation = BibliotecaResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Leitor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping()
    public ResponseEntity<BibliotecaResponseDto> addLivroBiblioteca(
            @Parameter(description = "Dados do livro a ser adicionado à biblioteca", required = true)
            @RequestBody @Valid BibliotecaRequestDto request) {
        BibliotecaResponseDto response = bibliotecaService.createBiblioteca(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para atualizar um livro na biblioteca do leitor
     * @param id ID do registro na biblioteca
     * @param request DTO com os dados atualizados do livro
     * @return ResponseEntity com os dados atualizados do livro
     */
    @Operation(summary = "Atualizar livro na biblioteca", description = "Atualiza as informações de um livro na biblioteca do leitor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = GenericSuccessResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado na biblioteca"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<GenericSuccessResponseDto<BibliotecaResponseDto>> updateLivroBiblioteca(
            @Parameter(description = "ID do registro na biblioteca", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Dados atualizados do livro", required = true)
            @RequestBody @Valid BibliotecaRequestDto request) {
        BibliotecaResponseDto response = bibliotecaService.updateBiblioteca(id, request);
        return ResponseEntity.ok(new GenericSuccessResponseDto<>("Registro da biblioteca atualizado com sucesso.", "biblioteca", response));
    }

    /**
     * Endpoint para obter a biblioteca paginada de um leitor
     * @param leitorId ID do leitor
     * @param pageable Configuração de paginação
     * @return ResponseEntity com a página de livros da biblioteca
     */
    @Operation(summary = "Listar biblioteca (paginado)", description = "Obtém a lista paginada de livros na biblioteca de um leitor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Biblioteca listada com sucesso",
                    content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "404", description = "Leitor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/all/{leitorId}")
    public ResponseEntity<Page<BibliotecaResponseDto>> getBiblioteca(
            @Parameter(description = "ID do leitor", required = true, example = "1")
            @PathVariable Long leitorId,
            @Parameter(description = "Parâmetros de paginação (page, size, sort)")
            Pageable pageable) {
        return ResponseEntity.ok(bibliotecaService.getBiblioteca(leitorId, pageable));
    }

    /**
     * Endpoint para obter um livro específico da biblioteca
     * @param id ID do registro na biblioteca
     * @return ResponseEntity com os dados do livro
     */
    @Operation(summary = "Obter livro da biblioteca", description = "Obtém um livro específico da biblioteca pelo ID do registro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro encontrado na biblioteca",
                    content = @Content(schema = @Schema(implementation = BibliotecaResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado na biblioteca"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BibliotecaResponseDto> getLivroBibliotecaById(
            @Parameter(description = "ID do registro na biblioteca", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(bibliotecaService.getLivroBibliotecaById(id));
    }

    /**
     * Endpoint para obter todos os livros da biblioteca de um leitor
     * @param id ID do leitor
     * @return ResponseEntity com a lista completa de livros
     */
    @Operation(summary = "Listar todos os livros da biblioteca", description = "Obtém todos os livros na biblioteca de um leitor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Biblioteca listada com sucesso",
                    content = @Content(schema = @Schema(implementation = BibliotecaResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Leitor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/list/{id}")
    public ResponseEntity<List<BibliotecaResponseDto>> getBiblioteca(
            @Parameter(description = "ID do leitor", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(bibliotecaService.getBiblioteca(id));
    }

    /**
     * Endpoint para remover um livro da biblioteca
     * @param id ID do registro na biblioteca
     * @return ResponseEntity vazio com status 204
     */
    @Operation(summary = "Remover livro da biblioteca", description = "Remove um livro da biblioteca do leitor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado na biblioteca"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeBiblioteca(
            @Parameter(description = "ID do registro na biblioteca", required = true, example = "1")
            @PathVariable Long id) {
        bibliotecaService.deleteBiblioteca(id);
        return ResponseEntity.noContent().build();
    }
}