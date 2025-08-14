package br.com.bookcheck.service.Usuario;


import br.com.bookcheck.controller.dto.request.Usuario.PublicacaoRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.PublicacaoResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Publicacao;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import br.com.bookcheck.domain.repository.PublicacaoRepository;
import br.com.bookcheck.domain.repository.UsuarioRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import br.com.bookcheck.mapper.Usuario.PublicacaoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class PublicacaoServiceImpl implements PublicacaoService {

    private final PublicacaoRepository publicacaoRepository;
    private final PublicacaoMapper publicacaoMapper;
    private final UsuarioRepository usuarioRepository;

    @Override
    public PublicacaoResponseDto createPublicacao(PublicacaoRequestDto request, String userEmail) {
        try {
            Usuario autor = usuarioRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new ServiceBusinessException("Usuário autor não encontrado."));

            Publicacao publicacao = publicacaoMapper.toEntity(request);
            publicacao.setAutor(autor);

            Publicacao savedPublicacao = publicacaoRepository.save(publicacao);
            return publicacaoMapper.toResponseDto(savedPublicacao);
        }catch (ServiceBusinessException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ServiceBusinessException("Erro ao salvar a publicação", e);
        }
    }

    @Override
    public PublicacaoResponseDto getPublicacaoById(Long id) {
        Publicacao Publicacao = publicacaoRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Publicação com ID não encontrada."));
        return publicacaoMapper.toResponseDto(Publicacao);
    }

    @Override
    public List<PublicacaoResponseDto> getPublicacoes(Long usuarioId) {
        return publicacaoMapper.toResponseDto(publicacaoRepository.findByAutorId(usuarioId));
    }

    @Override
    public Page<PublicacaoResponseDto> getPublicacoes(Long usuarioId, Pageable pageable) {
        return publicacaoMapper.toResponseDto(publicacaoRepository.findByAutorId(usuarioId,pageable));
    }

    @Override
    public void deletePublicacao(Long id) {
        publicacaoRepository.deleteById(id);
    }

    @Override
    public List<PublicacaoResponseDto> getAllPublicacoes() {
        return publicacaoMapper.toResponseDto(publicacaoRepository.findAll());
    }

    @Override
    public Page<PublicacaoResponseDto> getAllPublicacoes(Pageable pageable) {
        return publicacaoMapper.toResponseDto(publicacaoRepository.findAll(pageable));
    }
}