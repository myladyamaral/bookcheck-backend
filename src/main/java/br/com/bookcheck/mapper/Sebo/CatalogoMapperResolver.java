package br.com.bookcheck.mapper.Sebo;

import br.com.bookcheck.domain.entity.Sebo.Catalogo;
import br.com.bookcheck.domain.repository.CatalogoRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CatalogoMapperResolver {

    private final CatalogoRepository catalogoRepository;

    public Catalogo toCatalogo(Long id) {
        if (id == null) {
            return null;
        }
        return catalogoRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Tipo de Catalogo com ID " + id + " não encontrada"));
    }
}
