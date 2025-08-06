package br.com.bookcheck.service.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.PublicacaoRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.PublicacaoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PublicacaoService {
    PublicacaoResponseDto createPublicacao(PublicacaoRequestDto request);
    PublicacaoResponseDto getPublicacaoById(Long id);
    List<PublicacaoResponseDto> getPublicacoes(Long leitorId);
    Page<PublicacaoResponseDto> getPublicacoes(Long leitorId, Pageable pageable);
    void deletePublicacao(Long id);
    List<PublicacaoResponseDto> getAllPublicacoes();
    Page<PublicacaoResponseDto> getAllPublicacoes(Pageable pageable);
}
