package br.com.bookcheck.controller.dto.response;

import br.com.bookcheck.domain.enums.TipoUsuarioEnum;
import br.com.bookcheck.domain.enums.converter.TipoUsuarioConverter;
import jakarta.persistence.Convert;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class UsuarioResponseDto {
    private Long id;
    private String email;
    private EnderecoResponseDto endereco;
    private String telefone;
    private String descricao;
    private TipoUsuarioEnum tipoUsuario;
}