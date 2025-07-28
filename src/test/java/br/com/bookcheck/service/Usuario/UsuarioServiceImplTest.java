package br.com.bookcheck.service.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.UsuarioLeitorRequestDto;
import br.com.bookcheck.controller.dto.request.Usuario.UsuarioLeitorUpdateRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioLeitorResponseDto;
import br.com.bookcheck.domain.entity.Usuario.UsuarioLeitor;
import br.com.bookcheck.domain.enums.TipoUsuarioEnum;
import br.com.bookcheck.domain.repository.UsuarioLeitorRepository;
import br.com.bookcheck.domain.repository.UsuarioRepository;
import br.com.bookcheck.domain.repository.UsuarioSeboRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import br.com.bookcheck.mapper.Usuario.UsuarioMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceImplTest {

    private UsuarioRepository usuarioRepository;
    private UsuarioLeitorRepository usuarioLeitorRepository;
    private UsuarioSeboRepository usuarioSeboRepository;
    private PasswordEncoder passwordEncoder;
    private UsuarioMapper usuarioMapper;

    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    public void setup() {
        usuarioRepository = mock(UsuarioRepository.class);
        usuarioLeitorRepository = mock(UsuarioLeitorRepository.class);
        usuarioSeboRepository = mock(UsuarioSeboRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        usuarioMapper = mock(UsuarioMapper.class);

        usuarioService = new UsuarioServiceImpl(
                usuarioRepository,
                usuarioLeitorRepository,
                usuarioSeboRepository,
                passwordEncoder,
                usuarioMapper
        );
    }

    @Test
    public void createLeitor_deveCriarLeitorComSucesso_quandoDadosValidos() {
        // Arrange
        UsuarioLeitorRequestDto request = new UsuarioLeitorRequestDto();
        request.setNome("João da Silva");
        request.setCpf("12345678900");
        request.setEmail("joao@email.com");
        request.setSenha("senha123");

        UsuarioLeitor leitorEntity = new UsuarioLeitor();
        leitorEntity.setId(1L);
        leitorEntity.setCpf("12345678900");
        leitorEntity.setEmail("joao@email.com");
        leitorEntity.setTipoUsuario(TipoUsuarioEnum.LEITOR);

        UsuarioLeitorResponseDto response = (UsuarioLeitorResponseDto) UsuarioLeitorResponseDto.builder()
                .id(1L)
                .email("joao@email.com")
                .build();

        when(usuarioLeitorRepository.findByCpf("12345678900")).thenReturn(Optional.empty());
        when(usuarioMapper.toEntity(request)).thenReturn(leitorEntity);
        when(passwordEncoder.encode("senha123")).thenReturn("senhaCriptografada");
        when(usuarioRepository.save(any(UsuarioLeitor.class))).thenReturn(leitorEntity);
        when(usuarioMapper.toResponseDto(leitorEntity)).thenReturn(response);

        // Act
        UsuarioLeitorResponseDto resultado = usuarioService.createLeitor(request);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getEmail()).isEqualTo("joao@email.com");

        ArgumentCaptor<UsuarioLeitor> captor = ArgumentCaptor.forClass(UsuarioLeitor.class);
        verify(usuarioRepository).save(captor.capture());

        UsuarioLeitor salvo = captor.getValue();
        assertThat(salvo.getTipoUsuario()).isEqualTo(TipoUsuarioEnum.LEITOR);
        assertThat(salvo.getSenha()).isEqualTo("senhaCriptografada");
    }

    @Test
    public void createLeitor_deveLancarExcecao_quandoCpfJaExistente() {
        // Arrange
        UsuarioLeitorRequestDto request = new UsuarioLeitorRequestDto();
        request.setCpf("12345678900");
        request.setEmail("teste@email.com");

        when(usuarioLeitorRepository.findByCpf("12345678900"))
                .thenReturn(Optional.of(new UsuarioLeitor()));

        // Act + Assert
        assertThatThrownBy(() -> usuarioService.createLeitor(request))
                .isInstanceOf(ServiceBusinessException.class)
                .hasMessageContaining("Já existe um usuário com este CPF");
    }

    @Test
    public void createLeitor_deveCriarComCamposMinimos() {
        // Arrange
        UsuarioLeitorRequestDto request = new UsuarioLeitorRequestDto();
        request.setNome("Maria");
        request.setCpf("98765432100");
        request.setEmail("maria@email.com");
        request.setSenha("senha123");

        UsuarioLeitor leitorEntity = new UsuarioLeitor();
        leitorEntity.setId(2L);
        leitorEntity.setCpf("98765432100");
        leitorEntity.setEmail("maria@email.com");
        leitorEntity.setTipoUsuario(TipoUsuarioEnum.LEITOR);

        UsuarioLeitorResponseDto response = UsuarioLeitorResponseDto.builder()
                .id(2L)
                .email("maria@email.com")
                .build();

        when(usuarioLeitorRepository.findByCpf("98765432100")).thenReturn(Optional.empty());
        when(usuarioMapper.toEntity(request)).thenReturn(leitorEntity);
        when(passwordEncoder.encode("senha123")).thenReturn("senhaCriptografada2");
        when(usuarioRepository.save(any(UsuarioLeitor.class))).thenReturn(leitorEntity);
        when(usuarioMapper.toResponseDto(leitorEntity)).thenReturn(response);

        // Act
        UsuarioLeitorResponseDto resultado = usuarioService.createLeitor(request);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getEmail()).isEqualTo("maria@email.com");
        verify(usuarioRepository).save(any());
    }

    @Test
    public void createLeitor_deveLancarExcecao_quandoEmailJaExistente() {
        // Arrange
        UsuarioLeitorRequestDto request = new UsuarioLeitorRequestDto();
        request.setCpf("12345678900");
        request.setEmail("teste@email.com");

        when(usuarioLeitorRepository.findByCpf("12345678900")).thenReturn(Optional.empty());
        when(usuarioRepository.findByEmail("teste@email.com"))
                .thenReturn(Optional.of(new UsuarioLeitor()));

        // Act + Assert
        assertThatThrownBy(() -> usuarioService.createLeitor(request))
                .isInstanceOf(ServiceBusinessException.class)
                .hasMessageContaining("Já existe um usuário com este email");
    }

    @Test
    public void createLeitor_deveLancarExcecao_quandoSenhaForNula() {
        // Arrange
        UsuarioLeitorRequestDto request = new UsuarioLeitorRequestDto();
        request.setCpf("12345678900");
        request.setEmail("teste@email.com");
        request.setSenha(null);

        when(usuarioLeitorRepository.findByCpf("12345678900")).thenReturn(Optional.empty());

        // Act + Assert
        assertThatThrownBy(() -> usuarioService.createLeitor(request))
                .isInstanceOf(ServiceBusinessException.class)
                .hasMessageContaining("Senha é obrigatória");
    }

    @Test
    public void getLeitorById_deveRetornarLeitor_quandoIdExistente() {
        // Arrange
        Long id = 1L;
        UsuarioLeitor leitor = new UsuarioLeitor();
        leitor.setId(id);
        leitor.setEmail("joao@email.com");

        UsuarioLeitorResponseDto response = UsuarioLeitorResponseDto.builder()
                .id(id)
                .email("joao@email.com")
                .build();

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(leitor));
        when(usuarioMapper.toResponseDto(leitor)).thenReturn(response);

        // Act
        UsuarioLeitorResponseDto result = usuarioService.getLeitorById(id);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getEmail()).isEqualTo("joao@email.com");
    }

    @Test
    public void getLeitorById_deveLancarExcecao_quandoLeitorNaoEncontrado() {
        // Arrange
        Long id = 1L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        // Act + Assert
        assertThatThrownBy(() -> usuarioService.getLeitorById(id))
                .isInstanceOf(ServiceBusinessException.class)
                .hasMessageContaining("Leitor não encontrado");
    }

    @Test
    public void getAllLeitores_deveRetornarListaDeLeitores() {
        // Arrange
        UsuarioLeitor leitor1 = new UsuarioLeitor();
        leitor1.setId(1L);
        UsuarioLeitor leitor2 = new UsuarioLeitor();
        leitor2.setId(2L);

        UsuarioLeitorResponseDto dto1 = UsuarioLeitorResponseDto.builder().id(1L).build();
        UsuarioLeitorResponseDto dto2 = UsuarioLeitorResponseDto.builder().id(2L).build();

        when(usuarioRepository.findAllLeitores()).thenReturn(List.of(leitor1, leitor2));
        when(usuarioMapper.toResponseDto(leitor1)).thenReturn(dto1);
        when(usuarioMapper.toResponseDto(leitor2)).thenReturn(dto2);

        // Act
        var leitores = usuarioService.getAllLeitores();

        // Assert
        assertThat(leitores).hasSize(2);
        assertThat(leitores.get(0).getId()).isEqualTo(1L);
    }

    @Test
    public void updateLeitor_deveAtualizarComSucesso_quandoIdValido() {
        // Arrange
        Long id = 1L;
        UsuarioLeitorUpdateRequestDto request = new UsuarioLeitorUpdateRequestDto();
        request.setEmail("novo@email.com");

        UsuarioLeitor leitor = new UsuarioLeitor();
        leitor.setId(id);
        leitor.setEmail("antigo@email.com");

        UsuarioLeitor leitorAtualizado = new UsuarioLeitor();
        leitorAtualizado.setId(id);
        leitorAtualizado.setEmail("novo@email.com");

        UsuarioLeitorResponseDto response = UsuarioLeitorResponseDto.builder()
                .id(id)
                .email("novo@email.com")
                .build();

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(leitor));
        doAnswer(invocation -> {
            UsuarioLeitorUpdateRequestDto req = invocation.getArgument(0);
            UsuarioLeitor entity = invocation.getArgument(1);
            entity.setEmail(req.getEmail());
            return null;
        }).when(usuarioMapper).updateEntityFromDto(eq(request), eq(leitor));
        when(usuarioRepository.save(leitor)).thenReturn(leitorAtualizado);
        when(usuarioMapper.toResponseDto(leitorAtualizado)).thenReturn(response);

        // Act
        UsuarioLeitorResponseDto result = usuarioService.updateLeitor(id, request);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("novo@email.com");
    }

    @Test
    public void updateLeitor_deveLancarExcecao_quandoIdNaoExistente() {
        Long id = 99L;
        UsuarioLeitorUpdateRequestDto request = new UsuarioLeitorUpdateRequestDto();
        request.setEmail("novo@email.com");

        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> usuarioService.updateLeitor(id, request))
                .isInstanceOf(ServiceBusinessException.class)
                .hasMessageContaining("Leitor não encontrado");
    }

}
