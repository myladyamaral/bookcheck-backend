package br.com.bookcheck.service.Leitor;

import br.com.bookcheck.controller.dto.request.Leitor.LivroDesejadoRequestDto;
import br.com.bookcheck.controller.dto.response.Leitor.LivroDesejadoResponseDto;
import br.com.bookcheck.domain.entity.Livro.Livro;
import br.com.bookcheck.domain.entity.Leitor.LivroDesejado;
import br.com.bookcheck.domain.repository.LivroDesejadoRepository;
import br.com.bookcheck.domain.repository.LivroRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import br.com.bookcheck.mapper.Leitor.LivroDesejadoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LivroDesejadoServiceImplTest {

    private LivroDesejadoRepository livroDesejadoRepository;
    private LivroRepository livroRepository;
    private LivroDesejadoMapper livroDesejadoMapper;

    private LivroDesejadoServiceImpl service;

    @BeforeEach
    public void setup() {
        livroDesejadoRepository = mock(LivroDesejadoRepository.class);
        livroRepository = mock(LivroRepository.class);
        livroDesejadoMapper = mock(LivroDesejadoMapper.class);

        service = new LivroDesejadoServiceImpl(
                livroDesejadoRepository,
                livroDesejadoMapper,
                livroRepository
        );
    }

    @Test
    public void createLivroDesejado_deveCriarComSucesso_quandoLivroExiste() {
        // Arrange
        LivroDesejadoRequestDto request = new LivroDesejadoRequestDto(1L, 2L);
        Livro livro = new Livro();
        livro.setId(2L);

        LivroDesejado livroDesejado = new LivroDesejado();
        LivroDesejado livroDesejadoSalvo = new LivroDesejado();
        livroDesejadoSalvo.setId(10L);

        LivroDesejadoResponseDto response = new LivroDesejadoResponseDto();
        response.setId(10L);

        when(livroRepository.findById(2L)).thenReturn(Optional.of(livro));
        when(livroDesejadoMapper.toEntity(request)).thenReturn(livroDesejado);
        when(livroDesejadoRepository.save(livroDesejado)).thenReturn(livroDesejadoSalvo);
        when(livroDesejadoMapper.toResponseDto(livroDesejadoSalvo)).thenReturn(response);

        // Act
        LivroDesejadoResponseDto resultado = service.createLivroDesejado(request);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(10L);
    }

    @Test
    public void createLivroDesejado_deveLancarExcecao_quandoLivroNaoEncontrado() {
        // Arrange
        LivroDesejadoRequestDto request = new LivroDesejadoRequestDto(1L, 99L);
        when(livroRepository.findById(99L)).thenReturn(Optional.empty());

        // Act + Assert
        assertThatThrownBy(() -> service.createLivroDesejado(request))
                .isInstanceOf(ServiceBusinessException.class)
                .hasMessageContaining("Livro com ID 99 não encontrada");
    }

    @Test
    public void getLivroDesejadoById_deveRetornar_quandoIdValido() {
        LivroDesejado livroDesejado = new LivroDesejado();
        livroDesejado.setId(5L);
        LivroDesejadoResponseDto response = new LivroDesejadoResponseDto();
        response.setId(5L);

        when(livroDesejadoRepository.findById(5L)).thenReturn(Optional.of(livroDesejado));
        when(livroDesejadoMapper.toResponseDto(livroDesejado)).thenReturn(response);

        LivroDesejadoResponseDto resultado = service.getLivroDesejadoById(5L);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(5L);
    }

    @Test
    public void getLivroDesejadoById_deveLancarExcecao_quandoNaoEncontrado() {
        when(livroDesejadoRepository.findById(100L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getLivroDesejadoById(100L))
                .isInstanceOf(ServiceBusinessException.class)
                .hasMessageContaining("Livro Desejado não encontrada");
    }

    @Test
    public void getAllLivrosDesejados_deveRetornarLista() {
        LivroDesejado livro1 = new LivroDesejado();
        livro1.setId(1L);
        LivroDesejadoResponseDto dto1 = new LivroDesejadoResponseDto();
        dto1.setId(1L);

        when(livroDesejadoRepository.findByLeitorId(1L)).thenReturn(List.of(livro1));
        when(livroDesejadoMapper.toResponseDto(List.of(livro1))).thenReturn(List.of(dto1));

        List<LivroDesejadoResponseDto> resultado = service.getAllLivrosDesejados(1L);

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getId()).isEqualTo(1L);
    }

    @Test
    public void getAllLivrosDesejados_comPaginacao_deveRetornarPagina() {
        LivroDesejado livro = new LivroDesejado();
        livro.setId(2L);
        LivroDesejadoResponseDto dto = new LivroDesejadoResponseDto();
        dto.setId(2L);
        Pageable pageable = Pageable.ofSize(10);
        Page<LivroDesejado> page = new PageImpl<>(List.of(livro));
        Page<LivroDesejadoResponseDto> pageDto = new PageImpl<>(List.of(dto));

        when(livroDesejadoRepository.findByLeitorId(2L, pageable)).thenReturn(page);
        when(livroDesejadoMapper.toResponseDto(page)).thenReturn(pageDto);

        Page<LivroDesejadoResponseDto> resultado = service.getAllLivrosDesejados(2L, pageable);

        assertThat(resultado).hasSize(1);
        assertThat(resultado.getContent().get(0).getId()).isEqualTo(2L);
    }

    @Test
    public void deleteLivroDesejado_deveChamarDeleteById() {
        service.deleteLivroDesejado(3L);
        verify(livroDesejadoRepository).deleteById(3L);
    }
}
