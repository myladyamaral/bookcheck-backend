package br.com.bookcheck.service.Usuario;

import br.com.bookcheck.controller.dto.response.Usuario.PublicacaoResponseDto;

public interface CurtidaService {
    PublicacaoResponseDto curtirDescurtirPublicacao(Long publicacaoId, String userEmail);
}