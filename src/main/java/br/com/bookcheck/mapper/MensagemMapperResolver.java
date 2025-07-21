package br.com.bookcheck.mapper;

import br.com.bookcheck.domain.entity.Mensagem;
import br.com.bookcheck.domain.repository.MensagemRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MensagemMapperResolver {

    private final MensagemRepository MensagemRepository;

    public Mensagem toMensagem(Long id) {
        if (id == null) {
            return null;
        }
        return MensagemRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Tipo de Autor com ID " + id + " não encontrada"));
    }
}
