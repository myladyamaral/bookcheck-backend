package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Usuario.Curtida;
import br.com.bookcheck.domain.entity.Usuario.Publicacao;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurtidaRepository extends JpaRepository<Curtida, Long>, JpaSpecificationExecutor<Curtida>{

    Optional<Curtida> findById(Long id);

    Optional<Curtida> findByPublicacaoAndAutor(Publicacao publicacao, Usuario autor);
}
