package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.Leitor.LivroDesejadoRequestDto;
import br.com.bookcheck.controller.dto.response.Leitor.LivroDesejadoResponseDto;
import br.com.bookcheck.controller.dto.response.Livro.LivroResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioLeitorResponseDto;
import br.com.bookcheck.service.Leitor.LivroDesejadoService;
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

@WebMvcTest(LivroDesejadoController.class)
class LivroDesejadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LivroDesejadoService livroDesejadoService;

    @Autowired
    private ObjectMapper objectMapper;

    private LivroDesejadoRequestDto getMockRequestDto() {
        return LivroDesejadoRequestDto.builder()
                .leitorId(1L)
                .livroId(2L)
                .build();
    }

    private LivroDesejadoResponseDto getMockResponseDto() {
        UsuarioLeitorResponseDto leitor = UsuarioLeitorResponseDto.builder()
                .id(1L)
                .nome("João Leitor")
                .email("joao@email.com")
                .build();

        LivroResponseDto livro = LivroResponseDto.builder()
                .id(2L)
                .titulo("O Alienista")
                .build();

        return LivroDesejadoResponseDto.builder()
                .id(10L)
                .leitor(leitor)
                .livro(livro)
                .build();
    }

    @Test
    void deveAdicionarLivroDesejado() throws Exception {
        LivroDesejadoRequestDto request = getMockRequestDto();
        LivroDesejadoResponseDto response = getMockResponseDto();

        Mockito.when(livroDesejadoService.createLivroDesejado(any())).thenReturn(response);

        mockMvc.perform(post("/api/leitor/livroDesejado")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.livro.titulo").value("O Alienista"))
                .andExpect(jsonPath("$.leitor.nome").value("João Leitor"));
    }

    @Test
    void deveBuscarLivrosDesejadosPaginado() throws Exception {
        Long leitorId = 1L;
        LivroDesejadoResponseDto dto = getMockResponseDto();
        var page = new PageImpl<>(List.of(dto), PageRequest.of(0, 10), 1);

        Mockito.when(livroDesejadoService.getAllLivrosDesejados(eq(leitorId), any(PageRequest.class)))
                .thenReturn(page);

        mockMvc.perform(get("/api/leitor/livroDesejado/all/{leitorId}?page=0&size=10", leitorId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(dto.getId()))
                .andExpect(jsonPath("$.content[0].livro.autor").value("Machado de Assis"));
    }

    @Test
    void deveBuscarLivroDesejadoPorId() throws Exception {
        Long id = 10L;
        LivroDesejadoResponseDto dto = getMockResponseDto();

        Mockito.when(livroDesejadoService.getLivroDesejadoById(id)).thenReturn(dto);

        mockMvc.perform(get("/api/leitor/livroDesejado/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.livro.titulo").value("O Alienista"));
    }

    @Test
    void deveBuscarTodosLivrosDesejadosSemPaginacao() throws Exception {
        Long leitorId = 1L;
        LivroDesejadoResponseDto dto = getMockResponseDto();

        Mockito.when(livroDesejadoService.getAllLivrosDesejados(leitorId)).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/leitor/livroDesejado/list/{id}", leitorId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(dto.getId()))
                .andExpect(jsonPath("$[0].leitor.email").value("joao@email.com"));
    }

    @Test
    void deveRemoverLivroDesejado() throws Exception {
        Long id = 10L;

        mockMvc.perform(delete("/api/leitor/livroDesejado/{id}", id))
                .andExpect(status().isNoContent());
    }
}
