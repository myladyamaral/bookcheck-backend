package br.com.bookcheck.service.Usuario;


import br.com.bookcheck.component.SecurityTokenComponent;
import br.com.bookcheck.controller.dto.request.AuthenticationRequestDto;
import br.com.bookcheck.controller.dto.response.AuthenticationResponseDto;
import br.com.bookcheck.domain.repository.UsuarioRepository;
import br.com.bookcheck.domain.view.UsuarioDetailView;
import br.com.bookcheck.exception.ServiceBusinessException;
import br.com.bookcheck.exception.UnauthorizedException;
import br.com.bookcheck.mapper.Usuario.UsuarioDetailViewMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Service implementation for loading user-specific data for Spring Security.
 * This class implements the {@link UserDetailsService} interface, which is used by
 * Spring Security to retrieve user details during the authentication process.
 */
@Service
@Slf4j
public class UsuarioSecurityServiceImpl implements UsuarioSecurityService {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final UsuarioDetailViewMapper usuarioDetailViewMapper;
    private final SecurityTokenComponent securityTokenComponent;


    public UsuarioSecurityServiceImpl(UsuarioRepository usuarioRepository,
                                      AuthenticationManager authenticationManager,
                                      UsuarioDetailViewMapper usuarioDetailViewMapper,
                                      SecurityTokenComponent securityTokenComponent,
                                    @Value("${app.link.host}") String hostLink,
                                      @Value("${app.link.time-to-live-in-minutes}") Integer timeToLiveInMinutes) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.usuarioDetailViewMapper = usuarioDetailViewMapper;
        this.securityTokenComponent = securityTokenComponent;
    }

    @Override
    public AuthenticationResponseDto login(AuthenticationRequestDto authenticationRequestDto) throws ServiceBusinessException, NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            authenticationRequestDto.setUsername(authenticationRequestDto.getUsername().trim());

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequestDto.getUsername(),
                            authenticationRequestDto.getPassword())
            );
        } catch (BadCredentialsException | DisabledException e) {
            throw new UnauthorizedException("Acesso não autorizado para os dados informados", e);
        }

        final UsuarioDetailView userDetails = usuarioDetailViewMapper.toView(
                usuarioRepository.findByEmail(authenticationRequestDto.getUsername())
                        .orElseThrow(() -> new UnauthorizedException("Acesso não autorizado para os dados informados"))
        );

        final String token = securityTokenComponent.generateToken(userDetails);
        final String refreshToken = securityTokenComponent.generateRefreshToken(userDetails);

        return new AuthenticationResponseDto(token, refreshToken);
    }
}

