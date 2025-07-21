package br.com.bookcheck.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TipoUsuarioEnum {

    LEITOR(1, "Leitor"),
    SEBO(2, "Sebo");

    private final Integer codigo;
    private final String descricao;

    TipoUsuarioEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static TipoUsuarioEnum findByCodigo(Integer codigo) {
        return Arrays.stream(TipoUsuarioEnum.values())
                .filter(st -> st.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
}