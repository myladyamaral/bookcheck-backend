package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.Usuario.UsuarioLeitorRequestDto;
import br.com.bookcheck.controller.dto.request.Usuario.UsuarioSeboRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioLeitorResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioResumeResponseDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/usuario")
@RequiredArgsConstructor
@Tag(name = "Gestão de Usuários", description = "API para gerenciamento de usuários (leitores e sebos)")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Criar novo leitor", description = "Registra um novo usuário do tipo leitor no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Leitor criado com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioLeitorResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/leitor")
    public ResponseEntity<UsuarioLeitorResponseDto> createLeitor(
            @Parameter(description = "Dados do leitor a ser criado", required = true)
            @Valid @RequestBody UsuarioLeitorRequestDto request) {
        UsuarioLeitorResponseDto response = usuarioService.createLeitor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Obter leitor por ID", description = "Recupera os detalhes de um leitor pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leitor encontrado",
                    content = @Content(schema = @Schema(implementation = UsuarioLeitorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Leitor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/perfil-leitor/{id}")
    public ResponseEntity<UsuarioLeitorResponseDto> getLeitorById(
            @Parameter(description = "ID do leitor a ser obtido", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getLeitorById(id));
    }

    @Operation(summary = "Listar todos os leitores", description = "Obtém uma lista de todos os leitores cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de leitores retornada com sucesso",
            content = @Content(schema = @Schema(implementation = UsuarioLeitorResponseDto.class)))
    @GetMapping("/leitores")
    public ResponseEntity<List<UsuarioLeitorResponseDto>> getAllLeitores() {
        return ResponseEntity.ok(usuarioService.getAllLeitores());
    }

    @Operation(summary = "Criar novo sebo", description = "Registra um novo usuário do tipo sebo no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sebo criado com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioSeboResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/sebo")
    public ResponseEntity<UsuarioSeboResponseDto> createSebo(
            @Parameter(description = "Dados do sebo a ser criado", required = true)
            @RequestBody @Valid UsuarioSeboRequestDto request) {
        UsuarioSeboResponseDto response = usuarioService.createSebo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Obter sebo por ID", description = "Recupera os detalhes de um sebo pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sebo encontrado",
                    content = @Content(schema = @Schema(implementation = UsuarioSeboResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Sebo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/perfil-sebo/{id}")
    public ResponseEntity<UsuarioSeboResponseDto> getSeboById(
            @Parameter(description = "ID do sebo a ser obtido", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getSeboById(id));
    }

    @Operation(summary = "Listar todos os sebos", description = "Obtém uma lista de todos os sebos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de sebos retornada com sucesso",
            content = @Content(schema = @Schema(implementation = UsuarioSeboResponseDto.class)))
    @GetMapping("/sebos")
    public ResponseEntity<List<UsuarioSeboResponseDto>> getAllSebos() {
        return ResponseEntity.ok(usuarioService.getAllSebos());
    }

    @Operation(summary = "Listar todos os usuários ", description = "Obtém uma lista com dados básicos de todos os usuários (leitores e sebos)")
    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
            content = @Content(schema = @Schema(implementation = UsuarioResumeResponseDto.class)))
    @GetMapping("/list/all")
    public ResponseEntity<List<UsuarioResumeResponseDto>> getAll() {
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @Operation(summary = "Listar todos os usuários com paginação", description = "Obtém uma lista paginada com dados básicos de todos os usuários")
    @ApiResponse(responseCode = "200", description = "Lista paginada de usuários retornada com sucesso",
            content = @Content(schema = @Schema(implementation = UsuarioResumeResponseDto.class)))
    @GetMapping("/all")
    public ResponseEntity<Page<UsuarioResumeResponseDto>> getAll(
            @Parameter(description = "Parâmetros de paginação (página, tamanho, ordenação)")
            Pageable pageable) {
        return ResponseEntity.ok(usuarioService.getAll(pageable));
    }
}