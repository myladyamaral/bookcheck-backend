package br.com.bookcheck.domain.view;

import br.com.bookcheck.domain.entity.Endereco;
import br.com.bookcheck.domain.enums.TipoUsuarioEnum;
import br.com.bookcheck.domain.enums.converter.TipoUsuarioConverter;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class UsuarioDetailView implements UserDetails {
    private Long id;
    private String email;

    private String senha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    private String cep;

    private String telefone;

    private String descricao;

    @Column(name = "tipo_usuario", updatable = false, nullable = false)
    @Convert(converter = TipoUsuarioConverter.class)
    private TipoUsuarioEnum tipoUsuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" +tipoUsuario.name())
        );
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
