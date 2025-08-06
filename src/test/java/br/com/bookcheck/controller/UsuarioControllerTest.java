package br.com.bookcheck.controller;

import br.com.bookcheck.controller.dto.request.Usuario.UsuarioLeitorRequestDto;
import br.com.bookcheck.controller.dto.request.Usuario.UsuarioSeboRequestDto;
import br.com.bookcheck.controller.dto.request.Usuario.EnderecoRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioLeitorResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioSeboResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioResumeResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.EnderecoResponseDto;
import br.com.bookcheck.domain.enums.TipoUsuarioEnum;
import br.com.bookcheck.service.Usuario.UsuarioService;
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

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private EnderecoRequestDto mockEnderecoRequest() {
        return EnderecoRequestDto.builder()
                .cep("12345-678")
                .logradouro("Rua Teste")
                .numero("123")
                .bairro("Centro")
                .cidade("Cidade Teste")
                .build();
    }

    private EnderecoResponseDto mockEnderecoResponse() {
        return EnderecoResponseDto.builder()
                .cep("12345-678")
                .logradouro("Rua Teste")
                .numero("123")
                .bairro("Centro")
                .cidade("Cidade Teste")
                .build();
    }

    private UsuarioLeitorRequestDto mockLeitorRequest() {
        return UsuarioLeitorRequestDto.builder()
                .nome("Leitor Teste")
                .email("leitor@teste.com")
                .senha("123456")
                .cpf("123.456.789-00")
                .endereco(mockEnderecoRequest())
                .telefone("99999-9999")
                .descricao("Leitor assíduo")
                .build();
    }

    private UsuarioLeitorResponseDto mockLeitorResponse() {
        return UsuarioLeitorResponseDto.builder()
                .id(1L)
                .nome("Leitor Teste")
                .email("leitor@teste.com")
                .cpf("123.456.789-00")
                .tipoUsuario(TipoUsuarioEnum.LEITOR)
                .endereco(mockEnderecoResponse())
                .telefone("99999-9999")
                .descricao("Leitor assíduo")
                .build();
    }

    private UsuarioSeboRequestDto mockSeboRequest() {
        return UsuarioSeboRequestDto.builder()
                .nome("Sebo Teste")
                .email("sebo@teste.com")
                .senha("123456")
                .cnpj("12.345.678/0001-00")
                .endereco(mockEnderecoRequest())
                .telefone("88888-8888")
                .descricao("Sebo popular")
                .build();
    }

    private UsuarioSeboResponseDto mockSeboResponse() {
        return UsuarioSeboResponseDto.builder()
                .id(2L)
                .nome("Sebo Teste")
                .email("sebo@teste.com")
                .cnpj("12.345.678/0001-00")
                .tipoUsuario(TipoUsuarioEnum.SEBO)
                .endereco(mockEnderecoResponse())
                .telefone("88888-8888")
                .descricao("Sebo popular")
                .build();
    }

    private UsuarioResumeResponseDto mockUsuarioResumo(Long id, String nome, String descricao, TipoUsuarioEnum tipo) {
        return UsuarioResumeResponseDto.builder()
                .id(id)
                .nome(nome)
                .descricao(descricao)
                .tipo(tipo)
                .build();
    }

    @Test
    void deveCriarLeitor() throws Exception {
        var request = mockLeitorRequest();
        var response = mockLeitorResponse();

        Mockito.when(usuarioService.createLeitor(any())).thenReturn(response);

        mockMvc.perform(post("/api/usuario/leitor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.email").value(response.getEmail()));
    }

    @Test
    void deveBuscarLeitorPorId() throws Exception {
        var response = mockLeitorResponse();

        Mockito.when(usuarioService.getLeitorById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/usuario/perfil-leitor/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value("123.456.789-00"));
    }

    @Test
    void deveListarTodosLeitores() throws Exception {
        Mockito.when(usuarioService.getAllLeitores()).thenReturn(List.of(mockLeitorResponse()));

        mockMvc.perform(get("/api/usuario/leitores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tipoUsuario").value("LEITOR"));
    }

    @Test
    void deveCriarSebo() throws Exception {
        var request = mockSeboRequest();
        var response = mockSeboResponse();

        Mockito.when(usuarioService.createSebo(any())).thenReturn(response);

        mockMvc.perform(post("/api/usuario/sebo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.cnpj").value("12.345.678/0001-00"));
    }

    @Test
    void deveBuscarSeboPorId() throws Exception {
        var response = mockSeboResponse();

        Mockito.when(usuarioService.getSeboById(2L)).thenReturn(response);

        mockMvc.perform(get("/api/usuario/perfil-sebo/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("sebo@teste.com"));
    }

    @Test
    void deveListarTodosSebos() throws Exception {
        Mockito.when(usuarioService.getAllSebos()).thenReturn(List.of(mockSeboResponse()));

        mockMvc.perform(get("/api/usuario/sebos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tipoUsuario").value("SEBO"));
    }

    @Test
    void deveListarTodosUsuariosSemPaginacao() throws Exception {
        var resumo = mockUsuarioResumo(1L, "Leitor Teste", "leitor@teste.com", TipoUsuarioEnum.LEITOR);

        Mockito.when(usuarioService.getAll()).thenReturn(List.of(resumo));

        mockMvc.perform(get("/api/usuario/list/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Leitor Teste"));
    }

    @Test
    void deveListarTodosUsuariosComPaginacao() throws Exception {
        var page = new PageImpl<>(List.of(mockUsuarioResumo(1L, "Leitor Teste", "leitor@teste.com", TipoUsuarioEnum.LEITOR)));

        Mockito.when(usuarioService.getAll(any(PageRequest.class))).thenReturn(page);

        mockMvc.perform(get("/api/usuario/all?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].email").value("leitor@teste.com"));
    }
}
