package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.Usuario.PublicacaoRequestDto;
import br.com.bookcheck.controller.dto.response.GenericSuccessResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.PublicacaoResponseDto;
import br.com.bookcheck.service.Usuario.PublicacaoService;
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
@RequestMapping("api/usuario/publicacao")
@RequiredArgsConstructor
@Tag(name = "Gestão de Publicações", description = "API para gerenciamento de publicações dos usuários")
public class PublicacaoController {

    private final PublicacaoService publicacaoService;

    /**
     * Endpoint para criar uma nova publicação
     * @param request DTO contendo os dados da publicação
     * @return ResponseEntity com os dados da publicação criada
     */
    @Operation(summary = "Criar publicação", description = "Cria uma nova publicação para um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Publicação criada com sucesso",
                    content = @Content(schema = @Schema(implementation = PublicacaoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<PublicacaoResponseDto> createPublicacao(
            @Parameter(description = "Dados da publicação a ser criada", required = true)
            @RequestBody @Valid PublicacaoRequestDto request) {
        PublicacaoResponseDto response = publicacaoService.createPublicacao(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    /**
     * Endpoint para obter todas as publicações paginadas
     * @param pageable Configuração de paginação
     * @return ResponseEntity com a página de publicações
     */
    @Operation(summary = "Listar publicações (paginado)", description = "Obtém todas as publicações de forma paginada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publicações listadas com sucesso",
                    content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<Page<PublicacaoResponseDto>> getAllPublicacoes(
            @Parameter(description = "Parâmetros de paginação (page, size, sort)")
            Pageable pageable) {
        return ResponseEntity.ok(publicacaoService.getAllPublicacoes(pageable));
    }

    /**
     * Endpoint para obter todas as publicações paginadas
     * @return ResponseEntity com a página de publicações
     */
    @Operation(summary = "Listar publicações ", description = "Obtém todas as publicações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publicações listadas com sucesso",
                    content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/list")
    public ResponseEntity<List<PublicacaoResponseDto>> getListPublicacoes() {
        return ResponseEntity.ok(publicacaoService.getAllPublicacoes());
    }

    /**
     * Endpoint para obter publicações de um usuário específico (paginado)
     * @param usuarioId ID do usuário
     * @param pageable Configuração de paginação
     * @return ResponseEntity com a página de publicações do usuário
     */
    @Operation(summary = "Listar publicações por usuário (paginado)", description = "Obtém as publicações de um usuário específico de forma paginada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publicações listadas com sucesso",
                    content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Page<PublicacaoResponseDto>> getPublicacoesByUsuario(
            @Parameter(description = "ID do usuário", required = true, example = "1")
            @PathVariable Long usuarioId,
            @Parameter(description = "Parâmetros de paginação (page, size, sort)")
            Pageable pageable) {
        return ResponseEntity.ok(publicacaoService.getPublicacoes(usuarioId, pageable));
    }

    /**
     * Endpoint para obter uma publicação específica
     * @param id ID da publicação
     * @return ResponseEntity com os dados da publicação
     */
    @Operation(summary = "Obter publicação por ID", description = "Obtém uma publicação específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publicação encontrada",
                    content = @Content(schema = @Schema(implementation = PublicacaoResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Publicação não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PublicacaoResponseDto> getPublicacaoById(
            @Parameter(description = "ID da publicação", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(publicacaoService.getPublicacaoById(id));
    }

    /**
     * Endpoint para obter todas as publicações de um usuário
     * @param usuarioId ID do usuário
     * @return ResponseEntity com a lista de publicações do usuário
     */
    @Operation(summary = "Listar todas as publicações por usuário", description = "Obtém todas as publicações de um usuário específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publicações listadas com sucesso",
                    content = @Content(schema = @Schema(implementation = PublicacaoResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/usuario/{usuarioId}/all")
    public ResponseEntity<List<PublicacaoResponseDto>> getAllPublicacoesByUsuario(
            @Parameter(description = "ID do usuário", required = true, example = "1")
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(publicacaoService.getPublicacoes(usuarioId));
    }

    /**
     * Endpoint para remover uma publicação
     * @param id ID da publicação
     * @return ResponseEntity vazio com status 204
     */
    @Operation(summary = "Remover publicação", description = "Remove uma publicação existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Publicação removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Publicação não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublicacao(
            @Parameter(description = "ID da publicação", required = true, example = "1")
            @PathVariable Long id) {
        publicacaoService.deletePublicacao(id);
        return ResponseEntity.noContent().build();
    }
}