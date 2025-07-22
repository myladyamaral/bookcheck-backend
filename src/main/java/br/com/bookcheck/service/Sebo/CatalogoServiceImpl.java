package br.com.bookcheck.service.Sebo;


import br.com.bookcheck.controller.dto.request.Sebo.CatalogoRequestDto;
import br.com.bookcheck.controller.dto.response.Sebo.CatalogoResponseDto;
import br.com.bookcheck.domain.entity.Livro.Livro;
import br.com.bookcheck.domain.entity.Sebo.Catalogo;
import br.com.bookcheck.domain.repository.CatalogoRepository;
import br.com.bookcheck.domain.repository.LivroRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import br.com.bookcheck.mapper.Sebo.CatalogoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogoServiceImpl implements CatalogoService {

    private final CatalogoRepository catalogoRepository;
    private final CatalogoMapper catalogoMapper;
    private final LivroRepository livroRepository;

    @Override
    public CatalogoResponseDto createCatalogo(CatalogoRequestDto request) {
        try {
            Livro livro = livroRepository.findById(request.getLivroId())
                    .orElseThrow(() -> new ServiceBusinessException("Livro com ID " + request.getLivroId() + " não encontrada"));

            Catalogo catalogo = catalogoMapper.toEntity(request);
            Catalogo savedCatalogo = catalogoRepository.save(catalogo);
            return catalogoMapper.toResponseDto(savedCatalogo);
        }catch (ServiceBusinessException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ServiceBusinessException("Erro ao salvar a Catalogo", e);
        }
    }

    @Override
    public CatalogoResponseDto getCatalogoById(Long id) {
        Catalogo catalogo = catalogoRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Catalogo não encontrada."));
        return catalogoMapper.toResponseDto(catalogo);
    }

    @Override
    public List<CatalogoResponseDto> getAllCatalogos(Long seboId) {
        return catalogoMapper.toResponseDto(catalogoRepository.findBySeboId(seboId));
    }

    @Override
    public Page<CatalogoResponseDto> getAllCatalogos(Long seboId, Pageable pageable) {
        return catalogoMapper.toResponseDto(catalogoRepository.findAllBySeboId(seboId,pageable));
    }

    @Override
    public void deleteCatalogo(Long id) {
        catalogoRepository.deleteById(id);
    }
}

