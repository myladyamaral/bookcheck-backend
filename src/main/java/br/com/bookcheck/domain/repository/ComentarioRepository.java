package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Comentario;
import br.com.bookcheck.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>, JpaSpecificationExecutor<Usuario>{

    Optional<Comentario> findById(Long id);


}
