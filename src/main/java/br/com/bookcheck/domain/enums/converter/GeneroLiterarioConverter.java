package br.com.bookcheck.domain.enums.converter;

import br.com.bookcheck.domain.enums.GeneroLiterarioEnum;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GeneroLiterarioConverter extends GenericConverter<GeneroLiterarioEnum, Integer> {
    public GeneroLiterarioConverter() {
        super("codigo");

    }
}
