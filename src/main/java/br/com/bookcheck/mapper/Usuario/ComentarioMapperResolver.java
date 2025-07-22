package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.domain.entity.Usuario.Comentario;
import br.com.bookcheck.domain.repository.ComentarioRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComentarioMapperResolver {

    private final ComentarioRepository ComentarioRepository;

    public Comentario toComentario(Long id) {
        if (id == null) {
            return null;
        }
        return ComentarioRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Tipo de Comentario com ID " + id + " não encontrada"));
    }
}
