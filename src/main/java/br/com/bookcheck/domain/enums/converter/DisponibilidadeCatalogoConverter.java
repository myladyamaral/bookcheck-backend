package br.com.bookcheck.domain.enums.converter;

import br.com.bookcheck.domain.enums.DisponibilidadeCatalogoEnum;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DisponibilidadeCatalogoConverter extends GenericConverter<DisponibilidadeCatalogoEnum, Integer> {
    public DisponibilidadeCatalogoConverter() {
        super("codigo");

    }
}
