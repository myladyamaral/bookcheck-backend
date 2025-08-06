package br.com.bookcheck.service.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.ComentarioRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.ComentarioResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComentarioService {
    ComentarioResponseDto createComentario(ComentarioRequestDto request);
    ComentarioResponseDto getComentarioById(Long id);
    List<ComentarioResponseDto> getComentariosByPublicacao(Long publicacaoId);
    Page<ComentarioResponseDto> getComentariosByPublicacao(Long publicacaoId, Pageable pageable);
    void deleteComentario(Long id);
}
