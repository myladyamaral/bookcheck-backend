package br.com.bookcheck.mapper;

import br.com.bookcheck.domain.entity.Livro.Editora;
import br.com.bookcheck.domain.repository.EditoraRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EditoraMapperResolver {

    private final EditoraRepository editoraRepository;

    public Editora toEditora(Long id) {
        if (id == null) {
            return null;
        }
        return editoraRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Tipo de Autor com ID " + id + " não encontrada"));
    }
}
