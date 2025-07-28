package br.com.bookcheck.service.Usuario;

import br.com.bookcheck.component.SecurityTokenComponent;
import br.com.bookcheck.controller.dto.request.AuthenticationRequestDto;
import br.com.bookcheck.controller.dto.response.AuthenticationResponseDto;
import br.com.bookcheck.domain.entity.Usuario.UsuarioLeitor; // Importe as classes concretas
import br.com.bookcheck.domain.entity.Usuario.UsuarioSebo;   // Importe as classes concretas
import br.com.bookcheck.domain.repository.UsuarioRepository;
import br.com.bookcheck.domain.view.UsuarioDetailView;
import br.com.bookcheck.exception.UnauthorizedException;
import br.com.bookcheck.mapper.Usuario.UsuarioDetailViewMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioSecurityServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UsuarioDetailViewMapper usuarioDetailViewMapper;

    @Mock
    private SecurityTokenComponent securityTokenComponent;

    @InjectMocks
    private UsuarioSecurityServiceImpl usuarioSecurityService;

    @Test
    @DisplayName("Deve retornar tokens quando um UsuarioLeitor válido fizer login")
    void deveRetornarTokens_quandoUsuarioLeitorForValido() throws Exception {
        // Cenário (Arrange)
        var authRequest = new AuthenticationRequestDto("leitor@test.com", "password123");

        // **MUDANÇA PRINCIPAL: Instanciar um tipo concreto**
        var usuarioLeitor = new UsuarioLeitor();
        usuarioLeitor.setId(1L);
        usuarioLeitor.setEmail("leitor@test.com");

        var userDetails = new UsuarioDetailView();

        // Configuração dos mocks
        doNothing().when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        when(usuarioRepository.findByEmail("leitor@test.com")).thenReturn(Optional.of(usuarioLeitor));
        when(usuarioDetailViewMapper.toView(usuarioLeitor)).thenReturn(userDetails);
        when(securityTokenComponent.generateToken(userDetails)).thenReturn("leitor-access-token");
        when(securityTokenComponent.generateRefreshToken(userDetails)).thenReturn("leitor-refresh-token");

        // Ação (Act)
        AuthenticationResponseDto response = usuarioSecurityService.login(authRequest);

        // Verificação (Assert)
        assertNotNull(response);
        assertEquals("leitor-access-token", response.token());
        assertEquals("leitor-refresh-token", response.refreshToken());

        verify(usuarioRepository).findByEmail("leitor@test.com");
    }

    @Test
    @DisplayName("Deve retornar tokens quando um UsuarioSebo válido fizer login")
    void deveRetornarTokens_quandoUsuarioSeboForValido() throws Exception {
        // Cenário (Arrange)
        var authRequest = new AuthenticationRequestDto("sebo@test.com", "password123");

        // **MUDANÇA PRINCIPAL: Instanciar o outro tipo concreto**
        var usuarioSebo = new UsuarioSebo();
        usuarioSebo.setId(2L);
        usuarioSebo.setEmail("sebo@test.com");

        var userDetails = new UsuarioDetailView();

        // Configuração dos mocks
        doNothing().when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        when(usuarioRepository.findByEmail("sebo@test.com")).thenReturn(Optional.of(usuarioSebo));
        when(usuarioDetailViewMapper.toView(usuarioSebo)).thenReturn(userDetails);
        when(securityTokenComponent.generateToken(userDetails)).thenReturn("sebo-access-token");
        when(securityTokenComponent.generateRefreshToken(userDetails)).thenReturn("sebo-refresh-token");

        // Ação (Act)
        AuthenticationResponseDto response = usuarioSecurityService.login(authRequest);

        // Verificação (Assert)
        assertNotNull(response);
        assertEquals("sebo-access-token", response.token());
        assertEquals("sebo-refresh-token", response.refreshToken());

        verify(usuarioRepository).findByEmail("sebo@test.com");
    }

    @Test
    @DisplayName("Deve lançar UnauthorizedException quando as credenciais forem inválidas")
    void deveLancarExcecao_quandoCredenciaisForemInvalidas() {
        // Cenário (Arrange)
        var authRequest = new AuthenticationRequestDto("user@test.com", "wrong-password");

        // Esta parte não muda, pois a falha ocorre antes de carregar o usuário
        doThrow(new BadCredentialsException("Credenciais inválidas"))
                .when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        // Ação e Verificação (Act & Assert)
        UnauthorizedException exception = assertThrows(UnauthorizedException.class, () -> {
            usuarioSecurityService.login(authRequest);
        });

        assertEquals("Acesso não autorizado para os dados informados", exception.getMessage());
        verify(usuarioRepository, never()).findByEmail(anyString());
    }
}