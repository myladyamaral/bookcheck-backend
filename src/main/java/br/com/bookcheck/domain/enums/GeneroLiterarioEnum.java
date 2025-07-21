package br.com.bookcheck.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum GeneroLiterarioEnum {

    ROMANCE(1, "Romance"),
    FICCAO_CIENTIFICA(2, "Ficção Científica"),
    FANTASIA(3, "Fantasia"),
    TERROR(4, "Terror"),
    SUSPENSE(5, "Suspense"),
    MISTERIO(6, "Mistério"),
    DISTOPIA(7, "Distopia"),
    AVENTURA(8, "Aventura"),
    HISTORICO(9, "Histórico"),
    BIOGRAFIA(10, "Biografia"),
    POESIA(11, "Poesia"),
    DRAMA(12, "Drama"),
    COMEDIA(13, "Comédia"),
    INFANTIL(14, "Infantil"),
    JUVENIL(15, "Juvenil"),
    AUTO_AJUDA(16, "Autoajuda"),
    DIDATICO(17, "Didático"),
    CRONICA(18, "Crônica"),
    CONTO(19, "Conto"),
    QUADRINHOS(20, "Quadrinhos");

    private final Integer codigo;
    private final String descricao;

    GeneroLiterarioEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static GeneroLiterarioEnum findByCodigo(Integer codigo) {
        return Arrays.stream(GeneroLiterarioEnum.values())
                .filter(st -> st.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
}