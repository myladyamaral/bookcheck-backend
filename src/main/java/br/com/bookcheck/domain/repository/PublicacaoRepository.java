package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Usuario.Publicacao;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long>, JpaSpecificationExecutor<Usuario>{

    Optional<Publicacao> findById(Long id);
    List<Publicacao> findByUsuarioId(Long usuarioId);
    Page<Publicacao> findByUsuarioId(Long usuarioId, Pageable pageable);
    List<Publicacao> findAllPublicacoes();
    Page<Publicacao> findAllPublicacoes(Pageable pageable);

}
