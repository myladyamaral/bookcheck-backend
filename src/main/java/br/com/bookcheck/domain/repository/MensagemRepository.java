package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Mensagem;
import br.com.bookcheck.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long>, JpaSpecificationExecutor<Usuario>{

    Optional<Mensagem> findById(Long id);


}
