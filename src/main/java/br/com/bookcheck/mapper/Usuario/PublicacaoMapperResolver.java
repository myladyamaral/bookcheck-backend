package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.domain.entity.Usuario.Publicacao;
import br.com.bookcheck.domain.repository.PublicacaoRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PublicacaoMapperResolver {

    private final PublicacaoRepository PublicacaoRepository;

    public Publicacao toPublicacao(Long id) {
        if (id == null) {
            return null;
        }
        return PublicacaoRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Tipo de Autor com ID " + id + " não encontrada"));
    }
}
