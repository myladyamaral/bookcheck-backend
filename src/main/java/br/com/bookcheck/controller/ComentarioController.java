package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.Usuario.ComentarioRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.ComentarioResponseDto;
import br.com.bookcheck.service.Usuario.ComentarioService;
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

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("api/usuario/publicacao/comentario")
@RequiredArgsConstructor
@Tag(name = "Gestão de Comentários", description = "API para gerenciamento de comentários em publicações")
public class ComentarioController {

    private final ComentarioService comentarioService;

    /**
     * Endpoint para criar um novo comentário
     * @param request DTO contendo os dados do comentário
     * @param principal Objeto que representa o usuário autenticado
     * @return ResponseEntity com os dados do comentário criado
     */
    @Operation(summary = "Criar comentário", description = "Cria um novo comentário para uma publicação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comentário criado com sucesso",
                    content = @Content(schema = @Schema(implementation = ComentarioResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Publicação ou usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<ComentarioResponseDto> createComentario(
            @Parameter(description = "Dados do comentário a ser criado", required = true)
            @RequestBody @Valid ComentarioRequestDto request,
            Principal principal) {
        ComentarioResponseDto response = comentarioService.createComentario(request, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para obter comentários de uma publicação (paginado)
     * @param publicacaoId ID da publicação
     * @param pageable Configuração de paginação
     * @return ResponseEntity com a página de comentários
     */
    @Operation(summary = "Listar comentários por publicação (paginado)", description = "Obtém os comentários de uma publicação de forma paginada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentários listados com sucesso",
                    content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "404", description = "Publicação não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{publicacaoId}")
    public ResponseEntity<Page<ComentarioResponseDto>> getComentariosByPublicacao(
            @Parameter(description = "ID da publicação", required = true, example = "1")
            @PathVariable Long publicacaoId,
            @Parameter(description = "Parâmetros de paginação (page, size, sort)")
            Pageable pageable) {
        return ResponseEntity.ok(comentarioService.getComentariosByPublicacao(publicacaoId, pageable));
    }

    /**
     * Endpoint para obter um comentário específico
     * @param id ID do comentário
     * @return ResponseEntity com os dados do comentário
     */
    @Operation(summary = "Obter comentário por ID", description = "Obtém um comentário específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentário encontrado",
                    content = @Content(schema = @Schema(implementation = ComentarioResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Comentário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioResponseDto> getComentarioById(
            @Parameter(description = "ID do comentário", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(comentarioService.getComentarioById(id));
    }

    /**
     * Endpoint para obter todos os comentários de uma publicação
     * @param publicacaoId ID da publicação
     * @return ResponseEntity com a lista de comentários
     */
    @Operation(summary = "Listar todos os comentários por publicação", description = "Obtém todos os comentários de uma publicação específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentários listados com sucesso",
                    content = @Content(schema = @Schema(implementation = ComentarioResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Publicação não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{publicacaoId}/all")
    public ResponseEntity<List<ComentarioResponseDto>> getAllComentariosByPublicacao(
            @Parameter(description = "ID da publicação", required = true, example = "1")
            @PathVariable Long publicacaoId) {
        return ResponseEntity.ok(comentarioService.getComentariosByPublicacao(publicacaoId));
    }

    /**
     * Endpoint para remover um comentário
     * @param id ID do comentário
     * @return ResponseEntity vazio com status 204
     */
    @Operation(summary = "Remover comentário", description = "Remove um comentário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comentário removido com sucesso"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado"),
            @ApiResponse(responseCode = "404", description = "Comentário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(
            @Parameter(description = "ID do comentário", required = true, example = "1")
            @PathVariable Long id) {
        comentarioService.deleteComentario(id);
        return ResponseEntity.noContent().build();
    }
}