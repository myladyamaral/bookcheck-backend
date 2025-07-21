package br.com.bookcheck.mapper;

import br.com.bookcheck.domain.entity.Curtida;
import br.com.bookcheck.domain.repository.CurtidaRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurtidaMapperResolver {

    private final CurtidaRepository CurtidaRepository;

    public Curtida toCurtida(Long id) {
        if (id == null) {
            return null;
        }
        return CurtidaRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Tipo de Curtida com ID " + id + " não encontrada"));
    }
}
