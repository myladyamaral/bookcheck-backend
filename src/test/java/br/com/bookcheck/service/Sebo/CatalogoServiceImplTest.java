package br.com.bookcheck.service.Sebo;

import br.com.bookcheck.controller.dto.request.Sebo.CatalogoRequestDto;
import br.com.bookcheck.controller.dto.response.Sebo.CatalogoResponseDto;
import br.com.bookcheck.domain.entity.Livro.Livro;
import br.com.bookcheck.domain.entity.Sebo.Catalogo;
import br.com.bookcheck.domain.repository.CatalogoRepository;
import br.com.bookcheck.domain.repository.LivroRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import br.com.bookcheck.mapper.Sebo.CatalogoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CatalogoServiceImplTest {

    private CatalogoRepository catalogoRepository;
    private LivroRepository livroRepository;
    private CatalogoMapper catalogoMapper;
    private CatalogoServiceImpl catalogoService;

    @BeforeEach
    void setUp() {
        catalogoRepository = mock(CatalogoRepository.class);
        livroRepository = mock(LivroRepository.class);
        catalogoMapper = mock(CatalogoMapper.class);

        catalogoService = new CatalogoServiceImpl(
                catalogoRepository,
                catalogoMapper,
                livroRepository
        );
    }

    @Test
    void createCatalogo_deveCriarComSucesso_quandoDadosValidos() {
        CatalogoRequestDto request = CatalogoRequestDto.builder()
                .livroId(1L)
                .seboId(2L)
                .preco(30.0)
                .quantidade(5)
                .build();

        Livro livro = new Livro();
        livro.setId(1L);

        Catalogo catalogo = new Catalogo();
        catalogo.setLivro(livro);

        Catalogo saved = new Catalogo();
        saved.setId(10L);

        CatalogoResponseDto response = CatalogoResponseDto.builder()
                .id(10L)
                .build();

        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        when(catalogoMapper.toEntity(request)).thenReturn(catalogo);
        when(catalogoRepository.save(catalogo)).thenReturn(saved);
        when(catalogoMapper.toResponseDto(saved)).thenReturn(response);

        CatalogoResponseDto result = catalogoService.createCatalogo(request);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(10L);
    }

    @Test
    void createCatalogo_deveLancarExcecao_quandoLivroNaoEncontrado() {
        CatalogoRequestDto request = CatalogoRequestDto.builder()
                .livroId(99L)
                .build();

        when(livroRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> catalogoService.createCatalogo(request))
                .isInstanceOf(ServiceBusinessException.class)
                .hasMessageContaining("Livro com ID 99 não encontrada");
    }

    @Test
    void getCatalogoById_deveRetornarCatalogo_quandoIdValido() {
        Catalogo catalogo = new Catalogo();
        catalogo.setId(1L);

        CatalogoResponseDto responseDto = CatalogoResponseDto.builder().id(1L).build();

        when(catalogoRepository.findById(1L)).thenReturn(Optional.of(catalogo));
        when(catalogoMapper.toResponseDto(catalogo)).thenReturn(responseDto);

        CatalogoResponseDto result = catalogoService.getCatalogoById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void getCatalogoById_deveLancarExcecao_quandoNaoEncontrado() {
        when(catalogoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> catalogoService.getCatalogoById(1L))
                .isInstanceOf(ServiceBusinessException.class)
                .hasMessageContaining("Catalogo não encontrada");
    }

    @Test
    void getAllCatalogos_deveRetornarListaDeCatalogos() {
        Catalogo catalogo1 = new Catalogo();
        catalogo1.setId(1L);
        Catalogo catalogo2 = new Catalogo();
        catalogo2.setId(2L);

        CatalogoResponseDto dto1 = CatalogoResponseDto.builder().id(1L).build();
        CatalogoResponseDto dto2 = CatalogoResponseDto.builder().id(2L).build();

        when(catalogoRepository.findBySeboId(5L)).thenReturn(List.of(catalogo1, catalogo2));
        when(catalogoMapper.toResponseDto(List.of(catalogo1, catalogo2))).thenReturn(List.of(dto1, dto2));

        var result = catalogoService.getAllCatalogos(5L);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(1L);
    }

    @Test
    void deleteCatalogo_deveChamarDeleteById() {
        catalogoService.deleteCatalogo(1L);

        verify(catalogoRepository, times(1)).deleteById(1L);
    }
}
