package br.com.bookcheck.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EstadoConservacaoEnum {

    NOVO(1, "Novo"),
    SEMI_NOVO(2, "Semi-novo"),
    BOM(3, "Bom"),
    USADO(4, "Usado"),
    GASTO(5, "Gasto"),
    DANIFICADO(6, "Danificado"),
    RESTAURADO(7, "Restaurado"),
    RARO(8, "Raro");

    private final Integer codigo;
    private final String descricao;

    EstadoConservacaoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static EstadoConservacaoEnum findByCodigo(Integer codigo) {
        return Arrays.stream(EstadoConservacaoEnum.values())
                .filter(st -> st.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
}