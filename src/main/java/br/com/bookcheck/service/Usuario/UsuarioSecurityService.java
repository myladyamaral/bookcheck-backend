package br.com.bookcheck.service.Usuario;

import br.com.bookcheck.controller.dto.request.AuthenticationRequestDto;
import br.com.bookcheck.controller.dto.response.LoginResponseDto; // Alterado
import br.com.bookcheck.exception.ServiceBusinessException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface UsuarioSecurityService {
    LoginResponseDto login(AuthenticationRequestDto authenticationRequestDto) throws ServiceBusinessException, NoSuchAlgorithmException, InvalidKeySpecException;
}