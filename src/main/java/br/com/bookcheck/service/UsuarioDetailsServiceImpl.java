package br.com.bookcheck.service;

import br.com.bookcheck.domain.entity.Usuario;
import br.com.bookcheck.domain.repository.UsuarioRepository;
import br.com.bookcheck.domain.view.UsuarioDetailView;
import br.com.bookcheck.mapper.UsuarioDetailViewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation for loading user-specific data for Spring Security.
 * This class implements the {@link UserDetailsService} interface, which is used by
 * Spring Security to retrieve user details during the authentication process.
 */
@Service
@RequiredArgsConstructor
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioDetailViewMapper usuarioDetailViewMapper;

    @Override
    @Transactional(readOnly = true)
    public UsuarioDetailView loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return usuarioDetailViewMapper.toView(usuario);
    }


}

