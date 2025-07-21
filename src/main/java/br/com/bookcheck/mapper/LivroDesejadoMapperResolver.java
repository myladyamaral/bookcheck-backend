package br.com.bookcheck.mapper;

import br.com.bookcheck.domain.entity.Leitor.LivroDesejado;
import br.com.bookcheck.domain.repository.LivroDesejadoRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LivroDesejadoMapperResolver {

    private final LivroDesejadoRepository livroDesejadoRepository;

    public LivroDesejado toLivroDesejado(Long id) {
        if (id == null) {
            return null;
        }
        return livroDesejadoRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Tipo de LivroDesejado com ID " + id + " não encontrada"));
    }
}
