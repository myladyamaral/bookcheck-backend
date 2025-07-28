package br.com.bookcheck.service.Usuario;

import br.com.bookcheck.domain.entity.Usuario.UsuarioLeitor;
import br.com.bookcheck.domain.entity.Usuario.UsuarioSebo;
import br.com.bookcheck.domain.repository.UsuarioRepository;
import br.com.bookcheck.domain.view.UsuarioDetailView;
import br.com.bookcheck.mapper.Usuario.UsuarioDetailViewMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioDetailsServiceImplTest {

    private UsuarioRepository usuarioRepository;
    private UsuarioDetailViewMapper usuarioDetailViewMapper;
    private UsuarioDetailsServiceImpl usuarioDetailsService;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        usuarioDetailViewMapper = mock(UsuarioDetailViewMapper.class);
        usuarioDetailsService = new UsuarioDetailsServiceImpl(usuarioRepository, usuarioDetailViewMapper);
    }

    @Test
    @DisplayName("Deve retornar UsuarioDetailView quando o usuario for um UsuarioLeitor")
    void loadUserByUsername_DeveRetornarUsuarioDetailView_QuandoUsuarioLeitorExistir() {
        // Arrange
        String email = "leitor@email.com";
        UsuarioLeitor usuarioLeitor = new UsuarioLeitor();
        usuarioLeitor.setEmail(email);

        UsuarioDetailView expectedView = mock(UsuarioDetailView.class);

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuarioLeitor));
        when(usuarioDetailViewMapper.toView(usuarioLeitor)).thenReturn(expectedView);

        // Act
        UsuarioDetailView result = usuarioDetailsService.loadUserByUsername(email);

        // Assert
        assertNotNull(result);
        assertEquals(expectedView, result);
        verify(usuarioRepository).findByEmail(email);
        verify(usuarioDetailViewMapper).toView(usuarioLeitor);
    }

    @Test
    @DisplayName("Deve retornar UsuarioDetailView quando o usuario for um UsuarioSebo")
    void loadUserByUsername_DeveRetornarUsuarioDetailView_QuandoUsuarioSeboExistir() {
        // Arrange
        String email = "sebo@email.com";
        UsuarioSebo usuarioSebo = new UsuarioSebo();
        usuarioSebo.setEmail(email);

        UsuarioDetailView expectedView = mock(UsuarioDetailView.class);

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuarioSebo));
        when(usuarioDetailViewMapper.toView(usuarioSebo)).thenReturn(expectedView);

        // Act
        UsuarioDetailView result = usuarioDetailsService.loadUserByUsername(email);

        // Assert
        assertNotNull(result);
        assertEquals(expectedView, result);
        verify(usuarioRepository).findByEmail(email);
        verify(usuarioDetailViewMapper).toView(usuarioSebo);
    }

    @Test
    @DisplayName("Deve lançar UsernameNotFoundException quando o usuário não existir")
    void loadUserByUsername_DeveLancarExcecao_QuandoUsuarioNaoExistir() {
        // Arrange
        String email = "naoexiste@email.com";
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> usuarioDetailsService.loadUserByUsername(email));
        verify(usuarioRepository).findByEmail(email);
        verifyNoInteractions(usuarioDetailViewMapper);
    }
}
