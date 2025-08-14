package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.response.Search.SearchResultDto;
import br.com.bookcheck.service.OpenLibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
@Tag(name = "Busca Global", description = "API para realizar buscas na Open Library")
public class SearchController {

    private final OpenLibraryService openLibraryService;

    @Operation(summary = "Busca global", description = "Busca por livros, autores e editoras na Open Library")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<SearchResultDto> search(
            @Parameter(description = "Termo de busca", required = true)
            @RequestParam String query) {
        SearchResultDto results = openLibraryService.search(query);
        return ResponseEntity.ok(results);
    }
}
