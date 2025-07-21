package br.com.bookcheck.domain.enums.converter;

import br.com.bookcheck.domain.enums.EstadoConservacaoEnum;
import br.com.bookcheck.domain.enums.TipoUsuarioEnum;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EstadoConservacaoConverter extends GenericConverter<EstadoConservacaoEnum, Integer> {
    public EstadoConservacaoConverter() {
        super("codigo");

    }
}
