package br.com.bookcheck.service.Usuario;

import br.com.bookcheck.controller.dto.response.Usuario.PublicacaoResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Curtida;
import br.com.bookcheck.domain.entity.Usuario.Publicacao;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import br.com.bookcheck.domain.repository.CurtidaRepository;
import br.com.bookcheck.domain.repository.PublicacaoRepository;
import br.com.bookcheck.domain.repository.UsuarioRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import br.com.bookcheck.mapper.Usuario.PublicacaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurtidaServiceImpl implements CurtidaService {

    private final CurtidaRepository curtidaRepository;
    private final PublicacaoRepository publicacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PublicacaoMapper publicacaoMapper;

    @Override
    @Transactional
    public PublicacaoResponseDto curtirDescurtirPublicacao(Long publicacaoId, String userEmail) {
        Usuario usuario = usuarioRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ServiceBusinessException("Usuário não encontrado."));

        Publicacao publicacao = publicacaoRepository.findById(publicacaoId)
                .orElseThrow(() -> new ServiceBusinessException("Publicação não encontrada."));

        Optional<Curtida> curtidaExistenteOpt = curtidaRepository.findByPublicacaoAndAutor(publicacao, usuario);

        if (curtidaExistenteOpt.isPresent()) {
            Curtida curtidaExistente = curtidaExistenteOpt.get();
            publicacao.getCurtidas().remove(curtidaExistente);
            curtidaRepository.delete(curtidaExistente);
        } else {
            Curtida novaCurtida = new Curtida();
            novaCurtida.setAutor(usuario);
            novaCurtida.setPublicacao(publicacao);
            novaCurtida.setDataHora(LocalDateTime.now());
            publicacao.getCurtidas().add(novaCurtida);
            curtidaRepository.save(novaCurtida);
        }

        return publicacaoMapper.toResponseDto(publicacao);
    }
}