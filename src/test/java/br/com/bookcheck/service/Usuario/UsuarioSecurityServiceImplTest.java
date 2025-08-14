package br.com.bookcheck.service.Usuario;

import br.com.bookcheck.component.SecurityTokenComponent;
import br.com.bookcheck.controller.dto.request.AuthenticationRequestDto;
import br.com.bookcheck.controller.dto.response.LoginResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioLeitorResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioSeboResponseDto;
import br.com.bookcheck.domain.entity.Usuario.UsuarioLeitor;
import br.com.bookcheck.domain.entity.Usuario.UsuarioSebo;
import br.com.bookcheck.domain.repository.UsuarioRepository;
import br.com.bookcheck.domain.view.UsuarioDetailView;
import br.com.bookcheck.exception.UnauthorizedException;
import br.com.bookcheck.mapper.Usuario.UsuarioDetailViewMapper;
import br.com.bookcheck.mapper.Usuario.UsuarioMapper;
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

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioSecurityServiceImpl usuarioSecurityService;

    @Test
    @DisplayName("Deve retornar tokens e dados do usuário quando um UsuarioLeitor válido fizer login")
    void deveRetornarTokens_quandoUsuarioLeitorForValido() throws Exception {

        var authRequest = new AuthenticationRequestDto("leitor@test.com", "password123");

        var usuarioLeitor = new UsuarioLeitor();
        usuarioLeitor.setId(1L);
        usuarioLeitor.setEmail("leitor@test.com");
        usuarioLeitor.setNome("Leitor Teste");

        var userDetails = new UsuarioDetailView();

        var usuarioLeitorDto = UsuarioLeitorResponseDto.builder()
                .id(1L)
                .email("leitor@test.com")
                .nome("Leitor Teste")
                .build();

        doNothing().when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        when(usuarioRepository.findByEmail("leitor@test.com")).thenReturn(Optional.of(usuarioLeitor));
        when(usuarioDetailViewMapper.toView(usuarioLeitor)).thenReturn(userDetails);
        when(usuarioMapper.toResponseDto(usuarioLeitor)).thenReturn(usuarioLeitorDto);
        when(securityTokenComponent.generateToken(userDetails)).thenReturn("leitor-access-token");
        when(securityTokenComponent.generateRefreshToken(userDetails)).thenReturn("leitor-refresh-token");

        LoginResponseDto response = usuarioSecurityService.login(authRequest);

        assertNotNull(response);
        assertEquals("leitor-access-token", response.getToken());
        assertEquals("leitor-refresh-token", response.getRefreshToken());
        assertNotNull(response.getUser());
        assertEquals("Leitor Teste", response.getUser().getNome());

        verify(usuarioRepository).findByEmail("leitor@test.com");
    }

    @Test
    @DisplayName("Deve retornar tokens e dados do usuário quando um UsuarioSebo válido fizer login")
    void deveRetornarTokens_quandoUsuarioSeboForValido() throws Exception {

        var authRequest = new AuthenticationRequestDto("sebo@test.com", "password123");

        var usuarioSebo = new UsuarioSebo();
        usuarioSebo.setId(2L);
        usuarioSebo.setEmail("sebo@test.com");
        usuarioSebo.setNome("Sebo Teste");

        var userDetails = new UsuarioDetailView();

        var usuarioSeboDto = UsuarioSeboResponseDto.builder()
                .id(2L)
                .email("sebo@test.com")
                .nome("Sebo Teste")
                .build();


        doNothing().when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        when(usuarioRepository.findByEmail("sebo@test.com")).thenReturn(Optional.of(usuarioSebo));
        when(usuarioDetailViewMapper.toView(usuarioSebo)).thenReturn(userDetails);
        when(usuarioMapper.toResponseDto(usuarioSebo)).thenReturn(usuarioSeboDto);
        when(securityTokenComponent.generateToken(userDetails)).thenReturn("sebo-access-token");
        when(securityTokenComponent.generateRefreshToken(userDetails)).thenReturn("sebo-refresh-token");

        LoginResponseDto response = usuarioSecurityService.login(authRequest);

        assertNotNull(response);
        assertEquals("sebo-access-token", response.getToken());
        assertEquals("sebo-refresh-token", response.getRefreshToken());
        assertNotNull(response.getUser());
        assertEquals("Sebo Teste", response.getUser().getNome());

        verify(usuarioRepository).findByEmail("sebo@test.com");
    }

    @Test
    @DisplayName("Deve lançar UnauthorizedException quando as credenciais forem inválidas")
    void deveLancarExcecao_quandoCredenciaisForemInvalidas() {

        var authRequest = new AuthenticationRequestDto("user@test.com", "wrong-password");

        doThrow(new BadCredentialsException("Credenciais inválidas"))
                .when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        UnauthorizedException exception = assertThrows(UnauthorizedException.class, () -> {
            usuarioSecurityService.login(authRequest);
        });

        assertEquals("Acesso não autorizado para os dados informados", exception.getMessage());
        verify(usuarioRepository, never()).findByEmail(anyString());
    }
}