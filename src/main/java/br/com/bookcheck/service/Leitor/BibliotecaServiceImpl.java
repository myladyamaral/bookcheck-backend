package br.com.bookcheck.service.Leitor;


import br.com.bookcheck.controller.dto.request.Leitor.BibliotecaRequestDto;
import br.com.bookcheck.controller.dto.request.Leitor.BibliotecaUpdateRequestDto;
import br.com.bookcheck.controller.dto.request.Livro.LivroRequestDto;
import br.com.bookcheck.controller.dto.response.Leitor.BibliotecaResponseDto;
import br.com.bookcheck.domain.entity.Leitor.Biblioteca;
import br.com.bookcheck.domain.entity.Livro.Livro;
import br.com.bookcheck.domain.entity.Sebo.Catalogo;
import br.com.bookcheck.domain.repository.BibliotecaRepository;
import br.com.bookcheck.domain.repository.LivroRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import br.com.bookcheck.mapper.Leitor.BibliotecaMapper;
import br.com.bookcheck.service.OpenLibraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class BibliotecaServiceImpl implements BibliotecaService {

    private final BibliotecaRepository bibliotecaRepository;
    private final BibliotecaMapper bibliotecaMapper;
    private final OpenLibraryService openLibraryService;

    @Override
    public BibliotecaResponseDto createBiblioteca(BibliotecaRequestDto request) {
        try {
            // Busca o livro na OpenLibrary
            LivroRequestDto livroRequest = openLibraryService.searchBookByIsbn(request.getIsbn());

            if (livroRequest == null) {
                throw new ServiceBusinessException("Livro não encontrado na OpenLibrary para o ISBN: " + request.getIsbn());
            }

            Optional<Biblioteca> optionalBiblioteca = bibliotecaRepository.findByLeitorIdAndIsbn(request.getLeitorId(), request.getIsbn());

            if(optionalBiblioteca.isPresent()) {
                throw new ServiceBusinessException("Livro já cadastrado na biblioteca: " + request.getIsbn());
            }

            Biblioteca biblioteca = bibliotecaMapper.toEntity(request);
            Biblioteca savedBiblioteca = bibliotecaRepository.save(biblioteca);
            return bibliotecaMapper.toResponseDto(savedBiblioteca);
        }catch (ServiceBusinessException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ServiceBusinessException("Erro ao salvar ao adicionar livro a biblioteca", e);
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

