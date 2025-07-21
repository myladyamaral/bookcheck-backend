package br.com.bookcheck.service;

import br.com.bookcheck.controller.dto.request.CatalogoRequestDto;
import br.com.bookcheck.controller.dto.request.UsuarioSeboRequestDto;
import br.com.bookcheck.controller.dto.response.CatalogoResponseDto;
import br.com.bookcheck.controller.dto.response.UsuarioSeboResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CatalogoService {
    CatalogoResponseDto createCatalogo(CatalogoRequestDto request);
    CatalogoResponseDto getCatalogoById(Long id);
    List<CatalogoResponseDto> getAllCatalogos(Long seboId);
    Page<CatalogoResponseDto> getAllCatalogosPage(Long seboId, Pageable pageable);
    void deleteCatalogo(Long id);
}
