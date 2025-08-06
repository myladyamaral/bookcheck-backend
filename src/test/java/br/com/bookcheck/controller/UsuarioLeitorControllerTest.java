package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.Usuario.UsuarioLeitorUpdateRequestDto;
import br.com.bookcheck.controller.dto.response.GenericSuccessResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioLeitorResponseDto;
import br.com.bookcheck.service.Usuario.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioLeitorController.class)
class UsuarioLeitorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private UsuarioLeitorUpdateRequestDto updateRequestDto;
    private UsuarioLeitorResponseDto responseDto;

    @BeforeEach
    void setup() {
        // Monta um DTO de update para enviar no corpo da requisição
        updateRequestDto = UsuarioLeitorUpdateRequestDto.builder()
                .email("usuario@teste.com")
                .nome("Usuário Teste")
                .cpf("12345678901") // só exemplo, seu DTO tem campos corretos
                .dataNascimento(LocalDateTime.of(1990, 1, 1, 0, 0))
                .build();

        // Monta a resposta mockada que o serviço irá retornar
        responseDto = UsuarioLeitorResponseDto.builder()
                .id(1L)
                .email("usuario@teste.com")
                .nome("Usuário Teste")
                .cpf("12345678901")
                .dataNascimento(LocalDateTime.of(1990, 1, 1, 0, 0))
                .build();
    }

    @Test
    void updateLeitor_deveRetornar200ComRespostaEsperada() throws Exception {
        // Mocka o serviço para retornar a resposta montada
        Mockito.when(usuarioService.updateLeitor(eq(1L), any(UsuarioLeitorUpdateRequestDto.class)))
                .thenReturn(responseDto);

        // Executa a requisição PUT e valida o resultado
        mockMvc.perform(put("/api/leitor/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Leitor atualizado com sucesso."))
                .andExpect(jsonPath("$.data.usuarioLeitor.id").value(1))
                .andExpect(jsonPath("$.data.usuarioLeitor.email").value("usuario@teste.com"))
                .andExpect(jsonPath("$.data.usuarioLeitor.cpf").value("12345678901"))
                .andExpect(jsonPath("$.data.usuarioLeitor.nome").value("Usuário Teste"))
        // Você pode adicionar mais asserts conforme os campos retornados
        ;
    }
}
