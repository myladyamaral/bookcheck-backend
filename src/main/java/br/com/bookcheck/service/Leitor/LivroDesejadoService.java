package br.com.bookcheck.service.Leitor;

import br.com.bookcheck.controller.dto.request.Leitor.LivroDesejadoRequestDto;
import br.com.bookcheck.controller.dto.response.Leitor.LivroDesejadoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LivroDesejadoService {
    LivroDesejadoResponseDto createLivroDesejado(LivroDesejadoRequestDto request);
    LivroDesejadoResponseDto getLivroDesejadoById(Long id);
    List<LivroDesejadoResponseDto> getAllLivrosDesejados(Long seboId);
    Page<LivroDesejadoResponseDto> getAllLivrosDesejados(Long seboId, Pageable pageable);
    void deleteLivroDesejado(Long id);
}
