package br.com.bookcheck.service;


import br.com.bookcheck.controller.dto.request.CatalogoRequestDto;
import br.com.bookcheck.controller.dto.request.EnderecoRequestDto;
import br.com.bookcheck.controller.dto.request.UsuarioLeitorRequestDto;
import br.com.bookcheck.controller.dto.request.UsuarioSeboRequestDto;
import br.com.bookcheck.controller.dto.response.CatalogoResponseDto;
import br.com.bookcheck.controller.dto.response.UsuarioLeitorResponseDto;
import br.com.bookcheck.controller.dto.response.UsuarioSeboResponseDto;
import br.com.bookcheck.domain.entity.Endereco;
import br.com.bookcheck.domain.entity.Livro.Livro;
import br.com.bookcheck.domain.entity.Sebo.Catalogo;
import br.com.bookcheck.domain.entity.UsuarioLeitor;
import br.com.bookcheck.domain.entity.UsuarioSebo;
import br.com.bookcheck.domain.enums.TipoUsuarioEnum;
import br.com.bookcheck.domain.repository.CatalogoRepository;
import br.com.bookcheck.domain.repository.EnderecoRepository;
import br.com.bookcheck.domain.repository.LivroRepository;
import br.com.bookcheck.domain.repository.UsuarioRepository;
import br.com.bookcheck.exception.ServiceBusinessException;
import br.com.bookcheck.mapper.CatalogoMapper;
import br.com.bookcheck.mapper.EnderecoMapper;
import br.com.bookcheck.mapper.UsuarioMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogoServiceImpl implements CatalogoService {

    private final CatalogoRepository catalogoRepository;
    private final CatalogoMapper catalogoMapper;
    private final LivroRepository livroRepository;

    @Override
    public CatalogoResponseDto createCatalogo(CatalogoRequestDto request) {
        try {
            Livro livro = livroRepository.findById(request.getLivroId())
                    .orElseThrow(() -> new ServiceBusinessException("Livro com ID " + request.getLivroId() + " não encontrada"));

            Catalogo catalogo = catalogoMapper.toEntity(request);
            Catalogo savedCatalogo = catalogoRepository.save(catalogo);
            return catalogoMapper.toResponseDto(savedCatalogo);
        }catch (ServiceBusinessException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ServiceBusinessException("Erro ao salvar a Catalogo", e);
        }
    }

    @Override
    public CatalogoResponseDto getCatalogoById(Long id) {
        Catalogo catalogo = catalogoRepository.findById(id)
                .orElseThrow(() -> new ServiceBusinessException("Catalogo não encontrada."));
        return catalogoMapper.toResponseDto(catalogo);
    }

    @Override
    public List<CatalogoResponseDto> getAllCatalogos(Long seboId) {
        return catalogoMapper.toResponseDto(catalogoRepository.findBySeboId(seboId));
    }

    @Override
    public Page<CatalogoResponseDto> getAllCatalogosPage(Long seboId, Pageable pageable) {
        return catalogoMapper.toResponseDto(catalogoRepository.findAllBySeboId(seboId,pageable));
    }

    @Override
    public void deleteCatalogo(Long id) {
        catalogoRepository.deleteById(id);
    }
}

