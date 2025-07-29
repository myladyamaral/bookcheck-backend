package br.com.bookcheck.service.Usuario;


import br.com.bookcheck.controller.dto.request.Usuario.ComentarioRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.ComentarioResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Comentario;
import br.com.bookcheck.domain.repository.ComentarioRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import br.com.bookcheck.mapper.Usuario.ComentarioMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;

    @Override
    public ComentarioResponseDto createComentario(ComentarioRequestDto request) {
        try {

            Comentario Comentario = comentarioMapper.toEntity(request);
            Comentario savedComentario = comentarioRepository.save(Comentario);
            return comentarioMapper.toResponseDto(savedComentario);
        }catch (ServiceBusinessException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ServiceBusinessException("Erro ao salvar a publicação", e);
        }
    }

    @Override
    public ComentarioResponseDto getComentarioById(Long id) {
        Comentario Comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Comentário com ID não encontrada."));
        return comentarioMapper.toResponseDto(Comentario);
    }

    @Override
    public List<ComentarioResponseDto> getComentariosByPublicacao(Long publicacaoId) {
        return comentarioMapper.toResponseDto(comentarioRepository.findByPublicacaoId(publicacaoId));
    }

    @Override
    public Page<ComentarioResponseDto> getComentariosByPublicacao(Long publicacaoId, Pageable pageable) {
        return comentarioMapper.toResponseDto(comentarioRepository.findByPublicacaoId(publicacaoId,pageable));
    }

    @Override
    public void deleteComentario(Long id) {
        comentarioRepository.deleteById(id);
    }


}

