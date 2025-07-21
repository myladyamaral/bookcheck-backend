package br.com.bookcheck.service;


import br.com.bookcheck.controller.dto.request.EnderecoRequestDto;
import br.com.bookcheck.controller.dto.request.UsuarioLeitorRequestDto;
import br.com.bookcheck.controller.dto.request.UsuarioSeboRequestDto;
import br.com.bookcheck.controller.dto.response.UsuarioLeitorResponseDto;
import br.com.bookcheck.controller.dto.response.UsuarioSeboResponseDto;
import br.com.bookcheck.domain.entity.Endereco;
import br.com.bookcheck.domain.entity.UsuarioLeitor;
import br.com.bookcheck.domain.entity.UsuarioSebo;
import br.com.bookcheck.domain.enums.TipoUsuarioEnum;
import br.com.bookcheck.domain.repository.EnderecoRepository;
import br.com.bookcheck.domain.repository.UsuarioRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import br.com.bookcheck.mapper.EnderecoMapper;
import br.com.bookcheck.mapper.UsuarioMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;
    private final EnderecoMapper enderecoMapper;

    // Operações para UsuarioLeitor
    @Transactional
    @Override
    public UsuarioLeitorResponseDto createLeitor(UsuarioLeitorRequestDto request) {

        Endereco endereco = saveEndereco(request.getEndereco());

        UsuarioLeitor leitor = usuarioMapper.toEntity(request);
        leitor.setEndereco(endereco);
        leitor.setSenha(passwordEncoder.encode(request.getSenha()));
        leitor.setTipoUsuario(TipoUsuarioEnum.LEITOR);
        leitor = (UsuarioLeitor) usuarioRepository.save(leitor);
        return usuarioMapper.toResponseDto(leitor);
    }

    @Override
    public UsuarioLeitorResponseDto getLeitorById(Long id) {
        UsuarioLeitor leitor = (UsuarioLeitor) usuarioRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Leitor não encontrado"));
        return usuarioMapper.toResponseDto(leitor);
    }

    @Override
    public List<UsuarioLeitorResponseDto> getAllLeitores() {
        return usuarioRepository.findAllLeitores().stream()
                .map(usuarioMapper::toResponseDto)
                .toList();
    }

    // Operações para UsuarioSebo
    @Transactional
    @Override
    public UsuarioSeboResponseDto createSebo(UsuarioSeboRequestDto request) {
        Endereco endereco = saveEndereco(request.getEndereco());

        UsuarioSebo sebo = usuarioMapper.toEntity(request);
        sebo.setEndereco(endereco);
        sebo.setSenha(passwordEncoder.encode(request.getSenha()));
        sebo.setTipoUsuario(TipoUsuarioEnum.SEBO);
        sebo = (UsuarioSebo) usuarioRepository.save(sebo);
        return usuarioMapper.toResponseDto(sebo);
    }

    @Override
    public UsuarioSeboResponseDto getSeboById(Long id) {
        UsuarioSebo sebo = (UsuarioSebo) usuarioRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Sebo não encontrado"));
        return usuarioMapper.toResponseDto(sebo);
    }

    @Override
    public List<UsuarioSeboResponseDto> getAllSebos() {
        return usuarioRepository.findAllSebos().stream()
                .map(usuarioMapper::toResponseDto)
                .toList();
    }

    // Metodo genérico para deletar
    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }


    private Endereco saveEndereco(EnderecoRequestDto request){
        Endereco endereco = enderecoMapper.toEntity(request);
        endereco = enderecoRepository.save(endereco);

        return endereco;
    }
}

