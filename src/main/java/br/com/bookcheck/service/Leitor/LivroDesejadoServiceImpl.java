package br.com.bookcheck.service.Leitor;


import br.com.bookcheck.controller.dto.request.Leitor.LivroDesejadoRequestDto;
import br.com.bookcheck.controller.dto.response.Leitor.LivroDesejadoResponseDto;
import br.com.bookcheck.domain.entity.Livro.Livro;
import br.com.bookcheck.domain.entity.Leitor.LivroDesejado;
import br.com.bookcheck.domain.repository.LivroDesejadoRepository;
import br.com.bookcheck.domain.repository.LivroRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import br.com.bookcheck.mapper.Leitor.LivroDesejadoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class LivroDesejadoServiceImpl implements LivroDesejadoService {

    private final LivroDesejadoRepository livroDesejadoRepository;
    private final LivroDesejadoMapper livroDesejadoMapper;
    private final LivroRepository livroRepository;

    @Override
    public LivroDesejadoResponseDto createLivroDesejado(LivroDesejadoRequestDto request) {
        try {
            Livro livro = livroRepository.findById(request.getLivroId())
                    .orElseThrow(() -> new ServiceBusinessException("Livro com ID " + request.getLivroId() + " não encontrada"));

            LivroDesejado livroDesejado = livroDesejadoMapper.toEntity(request);
            LivroDesejado savedLivroDesejado = livroDesejadoRepository.save(livroDesejado);
            return livroDesejadoMapper.toResponseDto(savedLivroDesejado);
        }catch (ServiceBusinessException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ServiceBusinessException("Erro ao salvar a Livro Desejado", e);
        }
    }

    @Override
    public LivroDesejadoResponseDto getLivroDesejadoById(Long id) {
        LivroDesejado livroDesejado = livroDesejadoRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Livro Desejado não encontrada."));
        return livroDesejadoMapper.toResponseDto(livroDesejado);
    }

    @Override
    public List<LivroDesejadoResponseDto> getAllLivrosDesejados(Long leitorId) {
        return livroDesejadoMapper.toResponseDto(livroDesejadoRepository.findByLeitorId(leitorId));
    }

    @Override
    public Page<LivroDesejadoResponseDto> getAllLivrosDesejados(Long leitorId, Pageable pageable) {
        return livroDesejadoMapper.toResponseDto(livroDesejadoRepository.findByLeitorId(leitorId,pageable));
    }

    @Override
    public void deleteLivroDesejado(Long id) {
        livroDesejadoRepository.deleteById(id);
    }
}

