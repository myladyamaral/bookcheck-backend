package br.com.bookcheck.service.Sebo;

import br.com.bookcheck.controller.dto.request.Sebo.CatalogoRequestDto;
import br.com.bookcheck.controller.dto.response.Sebo.CatalogoResponseDto;
import br.com.bookcheck.domain.entity.Sebo.Catalogo;
import br.com.bookcheck.domain.enums.DisponibilidadeCatalogoEnum;
import br.com.bookcheck.domain.repository.CatalogoRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import br.com.bookcheck.mapper.Sebo.CatalogoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CatalogoServiceImplTest {

    private CatalogoRepository catalogoRepository;
    private CatalogoMapper catalogoMapper;
    private CatalogoServiceImpl catalogoService;

    @BeforeEach
    void setUp() {
        catalogoRepository = mock(CatalogoRepository.class);
        catalogoMapper = mock(CatalogoMapper.class);
        
        catalogoService = new CatalogoServiceImpl(
                catalogoRepository,
                catalogoMapper
        );
    }

    @Test
    void createCatalogo_deveCriarComSucesso_quandoDadosValidos() {
        CatalogoRequestDto request = CatalogoRequestDto.builder()
                .workId("OL1M")
                .seboId(2L)
                .preco(30.0)
                .quantidade(5)
                .build();

        Catalogo catalogo = new Catalogo(); // Objeto mock para o mapper
        Catalogo savedCatalogo = new Catalogo();
        savedCatalogo.setId(10L);

        CatalogoResponseDto responseDto = CatalogoResponseDto.builder()
                .id(10L)
                .build();

        when(catalogoRepository.findBySeboIdAndWorkId(2L, "OL1M")).thenReturn(Optional.empty());
        when(catalogoMapper.toEntity(request)).thenReturn(catalogo);
        when(catalogoRepository.save(catalogo)).thenReturn(savedCatalogo);
        when(catalogoMapper.toResponseDto(savedCatalogo)).thenReturn(responseDto);

        CatalogoResponseDto result = catalogoService.createCatalogo(request);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(10L);
    }

    @Test
    void createCatalogo_deveLancarExcecao_quandoLivroJaExisteNoCatalogo() {
        // ARRANGE
        CatalogoRequestDto request = CatalogoRequestDto.builder()
                .workId("OL1M")
                .seboId(2L)
                .build();

        when(catalogoRepository.findBySeboIdAndWorkId(2L, "OL1M"))
                .thenReturn(Optional.of(new Catalogo()));

        assertThatThrownBy(() -> catalogoService.createCatalogo(request))
                .isInstanceOf(ServiceBusinessException.class)
                .hasMessageContaining("Livro já cadastrado no catálogo");
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
        // ARRANGE
        Catalogo catalogo1 = new Catalogo();
        catalogo1.setId(1L);
        Catalogo catalogo2 = new Catalogo();
        catalogo2.setId(2L);
        List<Catalogo> catalogos = List.of(catalogo1, catalogo2);

        CatalogoResponseDto dto1 = CatalogoResponseDto.builder().id(1L).build();
        CatalogoResponseDto dto2 = CatalogoResponseDto.builder().id(2L).build();
        List<CatalogoResponseDto> dtos = List.of(dto1, dto2);

        when(catalogoRepository.findBySeboIdAndStatus(5L, DisponibilidadeCatalogoEnum.DISPONIVEL)).thenReturn(catalogos);
        when(catalogoMapper.toResponseDto(catalogos)).thenReturn(dtos);

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