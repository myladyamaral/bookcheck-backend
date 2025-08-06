package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.AuthenticationRequestDto;
import br.com.bookcheck.controller.dto.response.AuthenticationResponseDto;
import br.com.bookcheck.service.Usuario.UsuarioSecurityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@Import(br.com.bookcheck.config.SpringSecurityConfig.class) // importa a config real
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsuarioSecurityService usuarioSecurityService;

    @Test
    @DisplayName("Deve autenticar com sucesso e retornar token e refreshToken")
    void deveAutenticarComSucesso() throws Exception {
        // Arrange
        var requestDto = AuthenticationRequestDto.builder()
                .username("usuario@example.com")
                .password("senha123")
                .build();

        var responseDto = new AuthenticationResponseDto("token-jwt", "refresh-token-jwt");

        Mockito.when(usuarioSecurityService.login(any(AuthenticationRequestDto.class)))
                .thenReturn(responseDto);

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("token-jwt"))
                .andExpect(jsonPath("$.refreshToken").value("refresh-token-jwt"));
    }
}
