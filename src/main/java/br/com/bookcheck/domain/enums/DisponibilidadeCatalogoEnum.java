package br.com.bookcheck.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum DisponibilidadeCatalogoEnum {

    DISPONIVEL(1, "Disponível"),
    INDISPONIVEL(0, "Indisponível");

    private final Integer codigo;
    private final String descricao;

    DisponibilidadeCatalogoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static DisponibilidadeCatalogoEnum findByCodigo(Integer codigo) {
        return Arrays.stream(DisponibilidadeCatalogoEnum.values())
                .filter(st -> st.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
}