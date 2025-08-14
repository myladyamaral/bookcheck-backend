package br.com.bookcheck.service.Usuario;


import br.com.bookcheck.component.SecurityTokenComponent;
import br.com.bookcheck.controller.dto.request.AuthenticationRequestDto;
import br.com.bookcheck.controller.dto.response.LoginResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import br.com.bookcheck.domain.entity.Usuario.UsuarioLeitor;
import br.com.bookcheck.domain.entity.Usuario.UsuarioSebo;
import br.com.bookcheck.domain.repository.UsuarioRepository;
import br.com.bookcheck.domain.view.UsuarioDetailView;
import br.com.bookcheck.exception.ServiceBusinessException;
import br.com.bookcheck.exception.UnauthorizedException;
import br.com.bookcheck.mapper.Usuario.UsuarioDetailViewMapper;
import br.com.bookcheck.mapper.Usuario.UsuarioMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
@Slf4j
public class UsuarioSecurityServiceImpl implements UsuarioSecurityService {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final UsuarioDetailViewMapper usuarioDetailViewMapper;
    private final SecurityTokenComponent securityTokenComponent;
    private final UsuarioMapper usuarioMapper; // Dependência adicionada

    // Construtor atualizado
    public UsuarioSecurityServiceImpl(UsuarioRepository usuarioRepository,
                                      AuthenticationManager authenticationManager,
                                      UsuarioDetailViewMapper usuarioDetailViewMapper,
                                      SecurityTokenComponent securityTokenComponent,
                                      UsuarioMapper usuarioMapper,
                                      @Value("${app.link.host}") String hostLink,
                                      @Value("${app.link.time-to-live-in-minutes}") Integer timeToLiveInMinutes) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.usuarioDetailViewMapper = usuarioDetailViewMapper;
        this.securityTokenComponent = securityTokenComponent;
        this.usuarioMapper = usuarioMapper; // Injeção de dependência
    }

    // Método login atualizado
    @Override
    public LoginResponseDto login(AuthenticationRequestDto authenticationRequestDto) throws ServiceBusinessException, NoSuchAlgorithmException, InvalidKeySpecException {
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

        final Usuario usuario = usuarioRepository.findByEmail(authenticationRequestDto.getUsername())
                .orElseThrow(() -> new UnauthorizedException("Acesso não autorizado para os dados informados"));

        final UsuarioDetailView userDetails = usuarioDetailViewMapper.toView(usuario);

        final String token = securityTokenComponent.generateToken(userDetails);
        final String refreshToken = securityTokenComponent.generateRefreshToken(userDetails);

        UsuarioResponseDto userResponseDto;
        if (usuario instanceof UsuarioLeitor) {
            userResponseDto = usuarioMapper.toResponseDto((UsuarioLeitor) usuario);
        } else if (usuario instanceof UsuarioSebo) {
            userResponseDto = usuarioMapper.toResponseDto((UsuarioSebo) usuario);
        } else {
            // Caso genérico, se houver
            throw new ServiceBusinessException("Tipo de usuário desconhecido para mapeamento.");
        }

        return new LoginResponseDto(token, refreshToken, userResponseDto);
    }
}