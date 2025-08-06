package br.com.bookcheck.service.Sebo;

import br.com.bookcheck.controller.dto.request.Sebo.CatalogoRequestDto;
import br.com.bookcheck.controller.dto.request.Sebo.CatalogoUpdateRequestDto;
import br.com.bookcheck.controller.dto.response.Sebo.CatalogoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CatalogoService {
    CatalogoResponseDto createCatalogo(CatalogoRequestDto request);
    CatalogoResponseDto updateCatalogo(Long id, CatalogoUpdateRequestDto request);
    CatalogoResponseDto getCatalogoById(Long id);
    List<CatalogoResponseDto> getAllCatalogos(Long seboId);
    Page<CatalogoResponseDto> getAllCatalogos(Long seboId, Pageable pageable);
    void deleteCatalogo(Long id);
}
