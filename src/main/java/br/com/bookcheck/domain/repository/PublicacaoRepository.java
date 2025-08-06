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
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long>, JpaSpecificationExecutor<Publicacao>{

    Optional<Publicacao> findById(Long id);

    List<Publicacao> findByAutorId(Long autorId);
    Page<Publicacao> findByAutorId(Long usuarioId, Pageable pageable);


}
