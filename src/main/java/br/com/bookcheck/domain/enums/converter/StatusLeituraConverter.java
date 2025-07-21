package br.com.bookcheck.domain.enums.converter;

import br.com.bookcheck.domain.enums.StatusLeituraEnum;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusLeituraConverter extends GenericConverter<StatusLeituraEnum, Integer> {
    public StatusLeituraConverter() {
        super("codigo");

    }
}
