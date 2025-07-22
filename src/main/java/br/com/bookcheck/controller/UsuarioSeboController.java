package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.Usuario.UsuarioSeboRequestDto;
import br.com.bookcheck.controller.dto.response.GenericSuccessResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioSeboResponseDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/sebo")
@RequiredArgsConstructor
@Tag(name = "Gestão de Usuário Sebo", description = "API para gerenciamento de usuário Sebo")
public class UsuarioSeboController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Editar Sebo", description = "Edita usuário do tipo Sebo no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sebo editado com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioSeboResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<GenericSuccessResponseDto<UsuarioSeboResponseDto>> update(
            @Parameter(description = "Id do Sebo a ser editado", required = true)
            @Valid @PathVariable Long id,
            @Parameter(description = "Dados do Sebo a ser editado", required = true)
            @Valid @RequestBody UsuarioSeboRequestDto request) {

        UsuarioSeboResponseDto response = usuarioService.createSebo(request);
        return ResponseEntity.ok(new GenericSuccessResponseDto<>("Sebo atualizado com sucesso.", "usuarioSebo", response));
    }

}