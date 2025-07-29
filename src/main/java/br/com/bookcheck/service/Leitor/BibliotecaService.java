package br.com.bookcheck.service.Leitor;

import br.com.bookcheck.controller.dto.request.Leitor.BibliotecaRequestDto;
import br.com.bookcheck.controller.dto.response.Leitor.BibliotecaResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BibliotecaService {
    BibliotecaResponseDto createBiblioteca(BibliotecaRequestDto request);
    BibliotecaResponseDto updateBiblioteca(Long id,BibliotecaRequestDto request);
    BibliotecaResponseDto getLivroBibliotecaById(Long id);
    List<BibliotecaResponseDto> getBiblioteca(Long leitorId);
    Page<BibliotecaResponseDto> getBiblioteca(Long leitorId, Pageable pageable);
    void deleteBiblioteca(Long id);
}
