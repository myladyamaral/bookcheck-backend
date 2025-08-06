package br.com.bookcheck.service.Sebo;


import br.com.bookcheck.controller.dto.request.Leitor.BibliotecaUpdateRequestDto;
import br.com.bookcheck.controller.dto.request.Livro.LivroRequestDto;
import br.com.bookcheck.controller.dto.request.Sebo.CatalogoRequestDto;
import br.com.bookcheck.controller.dto.request.Sebo.CatalogoUpdateRequestDto;
import br.com.bookcheck.controller.dto.response.Leitor.BibliotecaResponseDto;
import br.com.bookcheck.controller.dto.response.Livro.LivroResponseDto;
import br.com.bookcheck.controller.dto.response.Sebo.CatalogoResponseDto;
import br.com.bookcheck.domain.entity.Leitor.Biblioteca;
import br.com.bookcheck.domain.entity.Livro.Livro;
import br.com.bookcheck.domain.entity.Sebo.Catalogo;
import br.com.bookcheck.domain.enums.DisponibilidadeCatalogoEnum;
import br.com.bookcheck.domain.repository.CatalogoRepository;
import br.com.bookcheck.domain.repository.LivroRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import br.com.bookcheck.mapper.Livro.LivroMapper;
import br.com.bookcheck.mapper.Sebo.CatalogoMapper;
import br.com.bookcheck.service.OpenLibraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogoServiceImpl implements CatalogoService {

    private final CatalogoRepository catalogoRepository;
    private final CatalogoMapper catalogoMapper;
    private final OpenLibraryService openLibraryService;

    @Override
    public CatalogoResponseDto createCatalogo(CatalogoRequestDto request) {
        try {
            // Busca o livro na OpenLibrary
            LivroRequestDto livroRequest = openLibraryService.searchBookByIsbn(request.getIsbn());

            if (livroRequest == null) {
                throw new ServiceBusinessException("Livro não encontrado na OpenLibrary para o ISBN: " + request.getIsbn());
            }

            Optional<Catalogo> optionalCatalogo = catalogoRepository.findBySeboIdAndIsbn(request.getSeboId(), request.getIsbn());

            if(optionalCatalogo.isPresent()) {
                throw new ServiceBusinessException("Livro já cadastrado no catálogo: " + request.getIsbn());
            }

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
    public CatalogoResponseDto updateCatalogo(Long id, CatalogoUpdateRequestDto request) {
        try{
            Catalogo catalogo = catalogoRepository.findById(id)
                    .orElseThrow(() -> new ServiceBusinessException("Registro com ID " + id + " não encontrado."));

            catalogoMapper.updateEntityFromDto(request, catalogo);
            Catalogo updated = catalogoRepository.save(catalogo);

            return catalogoMapper.toResponseDto(updated);

        } catch (ServiceBusinessException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ServiceBusinessException("Erro ao atualizar catalogo com ID " + id, e);
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
        return catalogoMapper.toResponseDto(catalogoRepository.findBySeboIdAndStatus(seboId, DisponibilidadeCatalogoEnum.DISPONIVEL));
    }

    @Override
    public Page<CatalogoResponseDto> getAllCatalogos(Long seboId, Pageable pageable) {
        return catalogoMapper.toResponseDto(catalogoRepository.findAllBySeboIdAndStatus(seboId,DisponibilidadeCatalogoEnum.DISPONIVEL,pageable));
    }

    @Override
    public void deleteCatalogo(Long id) {
        catalogoRepository.deleteById(id);
    }

}

