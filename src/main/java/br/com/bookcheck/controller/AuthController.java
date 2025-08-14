package br.com.bookcheck.controller;


import br.com.bookcheck.controller.dto.request.AuthenticationRequestDto;
import br.com.bookcheck.controller.dto.response.LoginResponseDto; // Alterado
import br.com.bookcheck.service.Usuario.UsuarioSecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioSecurityService usuarioSecurityService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequestDto) throws Exception {
        return ResponseEntity.ok(usuarioSecurityService.login(authenticationRequestDto));
    }
}