package br.com.bookcheck.service;

import br.com.bookcheck.controller.dto.request.UsuarioLeitorRequestDto;
import br.com.bookcheck.controller.dto.request.UsuarioSeboRequestDto;
import br.com.bookcheck.controller.dto.response.UsuarioLeitorResponseDto;
import br.com.bookcheck.controller.dto.response.UsuarioSeboResponseDto;

import java.util.List;

public interface UsuarioService {
    UsuarioLeitorResponseDto createLeitor(UsuarioLeitorRequestDto request);
    UsuarioLeitorResponseDto getLeitorById(Long id);
    public List<UsuarioLeitorResponseDto> getAllLeitores();
    UsuarioSeboResponseDto createSebo(UsuarioSeboRequestDto request);
    UsuarioSeboResponseDto getSeboById(Long id);
    List<UsuarioSeboResponseDto> getAllSebos();
    void deleteUsuario(Long id);
}
