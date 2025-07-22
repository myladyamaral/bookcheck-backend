package br.com.bookcheck.service.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.UsuarioLeitorRequestDto;
import br.com.bookcheck.controller.dto.request.Usuario.UsuarioLeitorUpdateRequestDto;
import br.com.bookcheck.controller.dto.request.Usuario.UsuarioSeboRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioLeitorResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioResumeResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioSeboResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioService {
    UsuarioLeitorResponseDto createLeitor(UsuarioLeitorRequestDto request);
    UsuarioLeitorResponseDto getLeitorById(Long id);
    List<UsuarioLeitorResponseDto> getAllLeitores();
    UsuarioLeitorResponseDto updateLeitor(Long id,UsuarioLeitorUpdateRequestDto request);

    UsuarioSeboResponseDto createSebo(UsuarioSeboRequestDto request);
    UsuarioSeboResponseDto getSeboById(Long id);
    List<UsuarioSeboResponseDto> getAllSebos();
    UsuarioSeboResponseDto updateSebo(Long id,UsuarioSeboRequestDto request);


    List<UsuarioResumeResponseDto> getAll();
    Page<UsuarioResumeResponseDto> getAll(Pageable pageable);
}
