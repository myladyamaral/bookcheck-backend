package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Publicacao;
import br.com.bookcheck.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long>, JpaSpecificationExecutor<Usuario>{

    Optional<Publicacao> findById(Long id);


}
