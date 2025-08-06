package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.Usuario.MensagemRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.MensagemResponseDto;
import br.com.bookcheck.service.Usuario.MensagemService;
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
@RequestMapping("api/usuario/chat")
@RequiredArgsConstructor
@Tag(name = "Gestão de Comentários", description = "API para gerenciamento de comentários em publicações")
public class MensagemController {

    private final MensagemService mensagemService;

    /**
     * Endpoint para criar um novo comentário
     * @param request DTO contendo os dados do comentário
     * @return ResponseEntity com os dados do comentário criado
     */
    @Operation(summary = "Criar comentário", description = "Cria um novo comentário para uma publicação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comentário criado com sucesso",
                    content = @Content(schema = @Schema(implementation = MensagemResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Publicação ou usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/{usuarioId}")
    public ResponseEntity<MensagemResponseDto> createMensagem(
            @Parameter(description = "Dados do comentário a ser criado", required = true)
            @PathVariable Long usuarioId,
            @RequestBody @Valid MensagemRequestDto request) {
        MensagemResponseDto response = mensagemService.createMensagem(usuarioId,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para obter um comentário específico
     * @param id ID do comentário
     * @return ResponseEntity com os dados do comentário
     */
    @Operation(summary = "Obter comentário por ID", description = "Obtém um comentário específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentário encontrado",
                    content = @Content(schema = @Schema(implementation = MensagemResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Comentário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MensagemResponseDto> getMensagemById(
            @Parameter(description = "ID do comentário", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(mensagemService.getMensagemById(id));
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
    public ResponseEntity<Void> deleteMensagem(
            @Parameter(description = "ID do comentário", required = true, example = "1")
            @PathVariable Long id) {
        mensagemService.deleteMensagem(id);
        return ResponseEntity.noContent().build();
    }
}