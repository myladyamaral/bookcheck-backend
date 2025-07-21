package br.com.bookcheck.mapper;

import br.com.bookcheck.domain.entity.Livro.Autor;
import br.com.bookcheck.domain.repository.AutorRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutorMapperResolver {

    private final AutorRepository autorRepository;

    public Autor toAutor(Long id) {
        if (id == null) {
            return null;
        }
        return autorRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Tipo de Autor com ID " + id + " não encontrada"));
    }
}
