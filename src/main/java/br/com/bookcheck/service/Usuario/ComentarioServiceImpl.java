package br.com.bookcheck.service.Usuario;


import br.com.bookcheck.controller.dto.request.Usuario.ComentarioRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.ComentarioResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Comentario;
import br.com.bookcheck.domain.entity.Usuario.Publicacao;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import br.com.bookcheck.domain.repository.ComentarioRepository;
import br.com.bookcheck.domain.repository.PublicacaoRepository;
import br.com.bookcheck.domain.repository.UsuarioRepository;
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
    private final UsuarioRepository usuarioRepository;
    private final PublicacaoRepository publicacaoRepository;

    @Override
    public ComentarioResponseDto createComentario(ComentarioRequestDto request, String userEmail) {
        try {
            Usuario autor = usuarioRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new ServiceBusinessException("Usuário autor não encontrado."));

            Publicacao publicacao = publicacaoRepository.findById(request.getPublicacaoId())
                    .orElseThrow(() -> new ServiceBusinessException("Publicação com ID " + request.getPublicacaoId() + " não encontrada."));

            Comentario comentario = comentarioMapper.toEntity(request);
            comentario.setAutor(autor);
            comentario.setPublicacao(publicacao);

            Comentario savedComentario = comentarioRepository.save(comentario);
            return comentarioMapper.toResponseDto(savedComentario);
        } catch (ServiceBusinessException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ServiceBusinessException("Erro ao salvar o comentário", e);
        }
    }

    @Override
    public ComentarioResponseDto getComentarioById(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Comentário com ID não encontrada."));
        return comentarioMapper.toResponseDto(comentario);
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