package br.com.bookcheck.mapper;

import br.com.bookcheck.domain.entity.Leitor.Biblioteca;
import br.com.bookcheck.domain.repository.BibliotecaRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BibliotecaMapperResolver {

    private final BibliotecaRepository bibliotecaRepository;

    public Biblioteca toBiblioteca(Long id) {
        if (id == null) {
            return null;
        }
        return bibliotecaRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Tipo de Biblioteca com ID " + id + " não encontrada"));
    }
}
