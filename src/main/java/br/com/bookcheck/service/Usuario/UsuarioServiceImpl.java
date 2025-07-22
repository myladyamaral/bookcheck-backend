package br.com.bookcheck.service.Usuario;


import br.com.bookcheck.controller.dto.request.Usuario.UsuarioLeitorRequestDto;
import br.com.bookcheck.controller.dto.request.Usuario.UsuarioLeitorUpdateRequestDto;
import br.com.bookcheck.controller.dto.request.Usuario.UsuarioResquestDto;
import br.com.bookcheck.controller.dto.request.Usuario.UsuarioSeboRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioLeitorResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioResumeResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioSeboResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import br.com.bookcheck.domain.entity.Usuario.UsuarioLeitor;
import br.com.bookcheck.domain.entity.Usuario.UsuarioSebo;
import br.com.bookcheck.domain.enums.TipoUsuarioEnum;
import br.com.bookcheck.domain.repository.UsuarioLeitorRepository;
import br.com.bookcheck.domain.repository.UsuarioRepository;
import br.com.bookcheck.domain.repository.UsuarioSeboRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import br.com.bookcheck.mapper.Usuario.UsuarioMapper;
import br.com.bookcheck.util.AppUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioLeitorRepository usuarioLeitorRepository;
    private final UsuarioSeboRepository usuarioSeboRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    // Operações para UsuarioLeitor
    @Transactional
    @Override
    public UsuarioLeitorResponseDto createLeitor(UsuarioLeitorRequestDto request) {
        validateToCreate(request);

        request.setCpf(AppUtils.removeMaskCpf(request.getCpf()));

        //valida campos obrigatórios
        if (!AppUtils.isValidCpf(request.getCpf())) {
            throw new ServiceBusinessException("CPF inválido.");
        }
        if (usuarioLeitorRepository.findByCpf(request.getCpf()).isPresent()) {
            throw new ServiceBusinessException("Já existe um usuário com este CPF.");
        }
        try {
            UsuarioLeitor leitor = usuarioMapper.toEntity(request);
            leitor.setSenha(passwordEncoder.encode(request.getSenha()));
            leitor.setTipoUsuario(TipoUsuarioEnum.LEITOR);

            UsuarioLeitor savedLeitor = (UsuarioLeitor) usuarioRepository.save(leitor);
            return usuarioMapper.toResponseDto(savedLeitor);

        } catch (ServiceBusinessException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ServiceBusinessException("Erro ao salvar usuário", e);
        }
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

    @Override
    public UsuarioLeitorResponseDto updateLeitor(Long id,UsuarioLeitorUpdateRequestDto request) {
        try{
            UsuarioLeitor existingLeitor = (UsuarioLeitor) usuarioRepository.findById(id)
                    .orElseThrow(() -> new ServiceBusinessException("Leitor com ID " + id + " não encontrado."));

            validateToUpdate(request, existingLeitor);

            usuarioMapper.updateEntityFromDto(request, existingLeitor);
            UsuarioLeitor updatedLeitor = usuarioRepository.save(existingLeitor);

            return usuarioMapper.toResponseDto(updatedLeitor);

        } catch (ServiceBusinessException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ServiceBusinessException("Erro ao atualizar leitor com ID " + id, e);
        }
    }

    // Operações para UsuarioSebo
    @Transactional
    @Override
    public UsuarioSeboResponseDto createSebo(UsuarioSeboRequestDto request) {
        validateToCreate(request);
        request.setCnpj(AppUtils.removeMaskCnpj(request.getCnpj()));

        //valida campos obrigatórios
        if (!request.getCnpj().isEmpty()){
            if (!AppUtils.isValidCpf(request.getCnpj())) {
                throw new ServiceBusinessException("CNPJ inválido.");
            }
            if (usuarioSeboRepository.findByCnpj(request.getCnpj()).isPresent()) {
                throw new ServiceBusinessException("Já existe um usuário com este CNPJ.");
            }
        }
        try {
            UsuarioSebo sebo = usuarioMapper.toEntity(request);
            sebo.setSenha(passwordEncoder.encode(request.getSenha()));
            sebo.setTipoUsuario(TipoUsuarioEnum.SEBO);

            UsuarioSebo savedSebo = (UsuarioSebo) usuarioRepository.save(sebo);
            return usuarioMapper.toResponseDto(savedSebo);

        } catch (ServiceBusinessException e) {
        throw e;
        } catch (RuntimeException e) {
            throw new ServiceBusinessException("Erro ao salvar usuário", e);
        }

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

    @Override
    public UsuarioSeboResponseDto updateSebo(Long id, UsuarioSeboRequestDto request) {
        try{
            UsuarioSebo existingSebo = (UsuarioSebo) usuarioRepository.findById(id)
                    .orElseThrow(() -> new ServiceBusinessException("Leitor com ID " + id + " não encontrado."));

            validateToUpdate(request, existingSebo);

            usuarioMapper.updateEntityFromDto(request, existingSebo);
            UsuarioSebo updatedSebo = usuarioRepository.save(existingSebo);

            return usuarioMapper.toResponseDto(updatedSebo);

        } catch (ServiceBusinessException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ServiceBusinessException("Erro ao atualizar sebo com ID " + id, e);
        }
    }

    @Override
    public List<UsuarioResumeResponseDto> getAll() {
        return usuarioMapper.toResponseDto(usuarioRepository.findAll());
    }

    @Override
    public Page<UsuarioResumeResponseDto> getAll(Pageable pageable) {
        Page<Usuario> usuariosResumePage = usuarioRepository.findAll(pageable);
        return usuariosResumePage.map(usuarioMapper::toResumeResponseDto);
    }

    private void validateToCreate (UsuarioResquestDto request) {
        if (!request.getEmail().trim().isEmpty()) {
            if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
                throw new ServiceBusinessException("Já existe um usuário com este e-mail.");
            }
        }
    }
    private void validateToUpdate (UsuarioResquestDto request, Usuario usuario) {
        if (!request.getEmail().equals(usuario.getEmail())) {

            Optional<Usuario> existingUsuarioOpt = usuarioRepository.findByEmail(request.getEmail());

            if (existingUsuarioOpt.isPresent()) {
                Usuario existingUsuario = existingUsuarioOpt.get();
                if(!existingUsuario.getId().equals(usuario.getId())) {
                    throw new ServiceBusinessException("Já existe um usuário com este e-mail.");
                }
            }
        }
    }
}

