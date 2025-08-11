package br.com.bookcheck.controller.dto.response.Usuario;

import br.com.bookcheck.domain.enums.TipoUsuarioEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UsuarioResponseDto {
    private Long id;
    private String nome;
    private String email;
    private EnderecoResponseDto endereco;
    private String telefone;
    private String descricao;
    private TipoUsuarioEnum tipoUsuario;
}