package br.com.bookcheck.mapper;

import br.com.bookcheck.domain.entity.Livro.Livro;
import br.com.bookcheck.domain.repository.LivroRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LivroMapperResolver {

    private final LivroRepository livroRepository;

    public Livro toLivro(Long id) {
        if (id == null) {
            return null;
        }
        return livroRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Tipo de Livro com ID " + id + " não encontrada"));
    }
}
