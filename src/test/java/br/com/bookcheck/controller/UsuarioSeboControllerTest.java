package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.Usuario.UsuarioSeboRequestDto;
import br.com.bookcheck.controller.dto.response.GenericSuccessResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioSeboResponseDto;
import br.com.bookcheck.domain.enums.TipoUsuarioEnum;
import br.com.bookcheck.service.Usuario.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioSeboController.class)
public class UsuarioSeboControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private UsuarioSeboRequestDto mockRequest() {
        return UsuarioSeboRequestDto.builder()
                .nome("Sebo Teste")
                .email("sebo@teste.com")
                .senha("123456")
                .cnpj("12.345.678/0001-00")
                .horario("08:00 - 18:00")
                // Endereço pode ser adicionado se quiser, conforme sua classe UsuarioResquestDto
                .build();
    }

    private UsuarioSeboResponseDto mockResponse() {
        return UsuarioSeboResponseDto.builder()
                .id(1L)
                .nome("Sebo Teste")
                .email("sebo@teste.com")
                .cnpj("12.345.678/0001-00")
                .horario("08:00 - 18:00")
                .tipoUsuario(TipoUsuarioEnum.SEBO)
                // Endereço pode ser adicionado se quiser, conforme sua classe UsuarioResponseDto
                .build();
    }

    @Test
    void deveAtualizarSeboComSucesso() throws Exception {
        var request = mockRequest();
        var response = mockResponse();

        Mockito.when(usuarioService.createSebo(any())).thenReturn(response);

        mockMvc.perform(put("/api/sebo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Sebo atualizado com sucesso."))
                .andExpect(jsonPath("$.data.usuarioSebo.id").value(response.getId()))
                .andExpect(jsonPath("$.data.usuarioSebo.email").value(response.getEmail()))
                .andExpect(jsonPath("$.data.usuarioSebo.cnpj").value(response.getCnpj()))
                .andExpect(jsonPath("$.data.usuarioSebo.horario").value(response.getHorario()));
    }
}
