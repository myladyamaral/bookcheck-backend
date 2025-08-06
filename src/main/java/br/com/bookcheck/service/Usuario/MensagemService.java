package br.com.bookcheck.service.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.MensagemRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.MensagemResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MensagemService {
    MensagemResponseDto createMensagem(Long remetenteId,MensagemRequestDto request);
    MensagemResponseDto getMensagemById(Long id);
    List<MensagemResponseDto> getMensagens(Long remetenteId, Long destinatarioId);
    Page<MensagemResponseDto> getMensagens(Long remetenteId, Long destinatarioId, Pageable pageable);
    void deleteMensagem(Long id);
}
