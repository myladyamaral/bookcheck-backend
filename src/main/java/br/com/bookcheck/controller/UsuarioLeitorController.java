package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.Usuario.UsuarioLeitorUpdateRequestDto;
import br.com.bookcheck.controller.dto.response.GenericSuccessResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioLeitorResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import br.com.bookcheck.domain.repository.UsuarioRepository;
import br.com.bookcheck.service.Usuario.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("api/leitor")
@RequiredArgsConstructor
@Tag(name = "Gestão de Usuário Leitor", description = "API para gerenciamento de usuário leitor")
public class UsuarioLeitorController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    @Operation(summary = "Editar leitor", description = "Edita usuário do tipo leitor no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leitor editado com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioLeitorResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<GenericSuccessResponseDto<UsuarioLeitorResponseDto>> update(
            @Parameter(description = "Id do leitor a ser editado", required = true)
            @Valid @PathVariable Long id,
            @Parameter(description = "Dados do leitor a ser editado", required = true)
            @Valid @RequestBody UsuarioLeitorUpdateRequestDto request,
            Principal principal) {

        String userEmail = principal.getName();
        Usuario usuarioLogado = usuarioRepository.findByEmail(userEmail)
                .orElseThrow(() -> new AccessDeniedException("Usuário não encontrado."));

        if (!usuarioLogado.getId().equals(id)) {
            throw new AccessDeniedException("Um usuário não pode editar o perfil de outro.");
        }

        UsuarioLeitorResponseDto response = usuarioService.updateLeitor(id, request);
        return ResponseEntity.ok(new GenericSuccessResponseDto<>("Leitor atualizado com sucesso.", "usuarioLeitor", response));
    }
}