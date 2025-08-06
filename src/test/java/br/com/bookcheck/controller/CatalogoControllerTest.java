package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.Sebo.CatalogoRequestDto;
import br.com.bookcheck.controller.dto.response.Livro.LivroResponseDto;
import br.com.bookcheck.controller.dto.response.Sebo.CatalogoResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioSeboResponseDto;
import br.com.bookcheck.domain.enums.DisponibilidadeCatalogoEnum;
import br.com.bookcheck.domain.enums.EstadoConservacaoEnum;
import br.com.bookcheck.service.Sebo.CatalogoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CatalogoController.class)
class CatalogoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatalogoService catalogoService;

    @Autowired
    private ObjectMapper objectMapper;

    private CatalogoRequestDto getMockRequestDto() {
        return CatalogoRequestDto.builder()
                .seboId(1L)
                .livroId(2L)
                .estadoConservacao(EstadoConservacaoEnum.BOM)
                .preco(45.90)
                .quantidade(5)
                .status(DisponibilidadeCatalogoEnum.DISPONIVEL)
                .build();
    }

    private CatalogoResponseDto getMockResponseDto() {
        UsuarioSeboResponseDto seboDto = UsuarioSeboResponseDto.builder()
                .id(1L)
                .nome("Sebo Central")
                .email("contato@sebocentral.com")
                .build();

        LivroResponseDto livroDto = LivroResponseDto.builder()
                .id(2L)
                .titulo("O Cortiço")
                .build();

        return CatalogoResponseDto.builder()
                .id(10L)
                .sebo(seboDto)
                .livro(livroDto)
                .estadoConservacao(EstadoConservacaoEnum.BOM)
                .preco(45.90)
                .quantidade(5)
                .status(DisponibilidadeCatalogoEnum.DISPONIVEL)
                .build();
    }

    @Test
    void deveCriarCatalogoComSucesso() throws Exception {
        CatalogoRequestDto request = getMockRequestDto();
        CatalogoResponseDto response = getMockResponseDto();

        Mockito.when(catalogoService.createCatalogo(any())).thenReturn(response);

        mockMvc.perform(post("/api/catalogo/sebo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.livro.titulo").value("O Cortiço"))
                .andExpect(jsonPath("$.sebo.nome").value("Sebo Central"));
    }

    @Test
    void deveBuscarCatalogosPaginadoPorSeboId() throws Exception {
        Long seboId = 1L;
        CatalogoResponseDto dto = getMockResponseDto();
        var page = new PageImpl<>(List.of(dto), PageRequest.of(0, 10), 1);

        Mockito.when(catalogoService.getAllCatalogos(eq(seboId), any(PageRequest.class))).thenReturn(page);

        mockMvc.perform(get("/api/catalogo/all/{seboId}?page=0&size=10", seboId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(dto.getId()))
                .andExpect(jsonPath("$.content[0].livro.titulo").value("O Cortiço"));
    }

    @Test
    void deveBuscarCatalogoPorId() throws Exception {
        Long id = 10L;
        CatalogoResponseDto dto = getMockResponseDto();
        Mockito.when(catalogoService.getCatalogoById(id)).thenReturn(dto);

        mockMvc.perform(get("/api/catalogo/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.livro.autor").value("Aluísio Azevedo"));
    }

    @Test
    void deveBuscarCatalogosPorSeboIdSemPaginacao() throws Exception {
        Long seboId = 1L;
        CatalogoResponseDto dto = getMockResponseDto();
        Mockito.when(catalogoService.getAllCatalogos(seboId)).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/catalogo/list/{seboId}", seboId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(dto.getId()))
                .andExpect(jsonPath("$[0].sebo.nome").value("Sebo Central"));
    }

    @Test
    void deveDeletarCatalogoComSucesso() throws Exception {
        Long id = 10L;

        mockMvc.perform(delete("/api/catalogo/sebo/{id}", id))
                .andExpect(status().isNoContent());
    }
}
