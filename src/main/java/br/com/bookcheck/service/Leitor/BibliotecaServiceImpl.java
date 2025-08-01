package br.com.bookcheck.service.Leitor;


import br.com.bookcheck.controller.dto.request.Leitor.BibliotecaRequestDto;
import br.com.bookcheck.controller.dto.request.Leitor.BibliotecaUpdateRequestDto;
import br.com.bookcheck.controller.dto.response.Leitor.BibliotecaResponseDto;
import br.com.bookcheck.domain.entity.Leitor.Biblioteca;
import br.com.bookcheck.domain.entity.Livro.Livro;
import br.com.bookcheck.domain.repository.BibliotecaRepository;
import br.com.bookcheck.domain.repository.LivroRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import br.com.bookcheck.mapper.Leitor.BibliotecaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class BibliotecaServiceImpl implements BibliotecaService {

    private final BibliotecaRepository bibliotecaRepository;
    private final BibliotecaMapper bibliotecaMapper;
    private final LivroRepository livroRepository;

    @Override
    public BibliotecaResponseDto createBiblioteca(BibliotecaRequestDto request) {
        try {
            Livro livro = livroRepository.findById(request.getLivroId())
                    .orElseThrow(() -> new ServiceBusinessException("Livro com ID " + request.getLivroId() + " não encontrada"));

            Biblioteca biblioteca = bibliotecaMapper.toEntity(request);
            Biblioteca savedBiblioteca = bibliotecaRepository.save(biblioteca);
            return bibliotecaMapper.toResponseDto(savedBiblioteca);
        }catch (ServiceBusinessException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ServiceBusinessException("Erro ao salvar a Livro Desejado", e);
        }
    }

    @Override
    public BibliotecaResponseDto updateBiblioteca(Long id, BibliotecaUpdateRequestDto request) {
        try{
            Biblioteca existingBiblioteca = bibliotecaRepository.findById(id)
                    .orElseThrow(() -> new ServiceBusinessException("Registro com ID " + id + " não encontrado."));

            bibliotecaMapper.updateEntityFromDto(request, existingBiblioteca);
            Biblioteca updatedLeitor = bibliotecaRepository.save(existingBiblioteca);

            return bibliotecaMapper.toResponseDto(updatedLeitor);

        } catch (ServiceBusinessException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ServiceBusinessException("Erro ao atualizar leitor com ID " + id, e);
        }
    }

    @Override
    public BibliotecaResponseDto getLivroBibliotecaById(Long id) {
        Biblioteca biblioteca = bibliotecaRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Livro Desejado não encontrada."));
        return bibliotecaMapper.toResponseDto(biblioteca);
    }

    @Override
    public List<BibliotecaResponseDto> getBiblioteca(Long leitorId) {
        return bibliotecaMapper.toResponseDto(bibliotecaRepository.findByLeitorId(leitorId));
    }

    @Override
    public Page<BibliotecaResponseDto> getBiblioteca(Long leitorId, Pageable pageable) {
        return bibliotecaMapper.toResponseDto(bibliotecaRepository.findByLeitorId(leitorId,pageable));
    }

    @Override
    public void deleteBiblioteca(Long id) {
        bibliotecaRepository.deleteById(id);
    }
}

