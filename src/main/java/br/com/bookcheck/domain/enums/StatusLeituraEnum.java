package br.com.bookcheck.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum StatusLeituraEnum {

    NAO_INICIADO(1, "Não iniciado"),
    EM_ANDAMENTO(2, "Em andamento"),
    PAUSADO(3, "Pausado"),
    ABANDONADO(4, "Abandonado"),
    COMPLETO(5, "Completo"),
    RELENDO(6, "Relendo");

    private final Integer codigo;
    private final String descricao;

    StatusLeituraEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static StatusLeituraEnum findByCodigo(Integer codigo) {
        return Arrays.stream(StatusLeituraEnum.values())
                .filter(st -> st.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
}