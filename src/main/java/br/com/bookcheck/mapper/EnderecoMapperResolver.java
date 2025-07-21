package br.com.bookcheck.mapper;

import br.com.bookcheck.domain.entity.Endereco;
import br.com.bookcheck.domain.repository.EnderecoRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnderecoMapperResolver {

    private final EnderecoRepository EnderecoRepository;

    public Endereco toEndereco(Long id) {
        if (id == null) {
            return null;
        }
        return EnderecoRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Tipo de Autor com ID " + id + " não encontrada"));
    }
}
