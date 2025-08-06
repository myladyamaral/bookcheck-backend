package br.com.bookcheck.service.Usuario;


import br.com.bookcheck.controller.dto.request.Usuario.MensagemRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.MensagemResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Mensagem;
import br.com.bookcheck.domain.repository.MensagemRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import br.com.bookcheck.mapper.Usuario.MensagemMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class MensagemServiceImpl implements MensagemService {

    private final MensagemRepository mensagemRepository;
    private final MensagemMapper mensagemMapper;

    @Override
    public MensagemResponseDto createMensagem(Long remetenteId,MensagemRequestDto request) {
        try {

            Mensagem Mensagem = mensagemMapper.toEntity(request);
            Mensagem savedMensagem = mensagemRepository.save(Mensagem);
            return mensagemMapper.toResponseDto(savedMensagem);
        }catch (ServiceBusinessException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ServiceBusinessException("Erro ao salvar a publicação", e);
        }
    }

    @Override
    public MensagemResponseDto getMensagemById(Long id) {
        Mensagem Mensagem = mensagemRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Publicação com ID não encontrada."));
        return mensagemMapper.toResponseDto(Mensagem);
    }

    @Override
    public List<MensagemResponseDto> getMensagens(Long remetenteId, Long destinatarioId) {
        return mensagemMapper.toResponseDto(mensagemRepository.findByRemetenteIdAndDestinatarioId(remetenteId,
                destinatarioId));
    }

    @Override
    public Page<MensagemResponseDto> getMensagens(Long remetenteId, Long destinatarioId, Pageable pageable) {
        return mensagemMapper.toResponseDto(mensagemRepository.findByRemetenteIdAndDestinatarioId(remetenteId,
                destinatarioId, pageable));
    }

    @Override
    public void deleteMensagem(Long id) {
        mensagemRepository.deleteById(id);
    }

}

