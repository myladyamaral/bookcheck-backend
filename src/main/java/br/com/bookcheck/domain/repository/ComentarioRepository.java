package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Usuario.Comentario;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>, JpaSpecificationExecutor<Usuario>{

    Optional<Comentario> findById(Long id);


    List<Comentario> findByPublicacaoId(Long publicacaoId);
    Page<Comentario> findByPublicacaoId(Long publicacaoId, Pageable pageable);
}
