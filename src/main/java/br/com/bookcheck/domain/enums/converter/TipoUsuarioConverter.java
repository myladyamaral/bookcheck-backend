package br.com.bookcheck.domain.enums.converter;

import br.com.bookcheck.domain.enums.TipoUsuarioEnum;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoUsuarioConverter extends GenericConverter<TipoUsuarioEnum, Integer> {
    public TipoUsuarioConverter() {
        super("codigo");

    }
}
