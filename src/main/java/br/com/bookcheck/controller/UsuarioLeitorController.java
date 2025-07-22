package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.Usuario.UsuarioLeitorRequestDto;
import br.com.bookcheck.controller.dto.request.Usuario.UsuarioLeitorUpdateRequestDto;
import br.com.bookcheck.controller.dto.response.GenericSuccessResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioLeitorResponseDto;
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
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/leitor")
@RequiredArgsConstructor
@Tag(name = "Gestão de Usuário Leitor", description = "API para gerenciamento de usuário leitor")
public class UsuarioLeitorController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Editar leitor", description = "Edita usuário do tipo leitor no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Leitor editado com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioLeitorResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<GenericSuccessResponseDto<UsuarioLeitorResponseDto>> update(
            @Parameter(description = "Id do leitor a ser editado", required = true)
            @Valid @PathVariable Long id,
            @Parameter(description = "Dados do leitor a ser editado", required = true)
            @Valid @RequestBody UsuarioLeitorUpdateRequestDto request) {

        UsuarioLeitorResponseDto response = usuarioService.updateLeitor(id,request);
        return ResponseEntity.ok(new GenericSuccessResponseDto<>("Leitor atualizado com sucesso.", "usuarioLeitor", response));
    }

}