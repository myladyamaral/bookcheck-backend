package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Usuario.Mensagem;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long>, JpaSpecificationExecutor<Mensagem>{

    Optional<Mensagem> findById(Long id);

    List<Mensagem> findByRemetenteIdAndDestinatarioId(Long remetenteId, Long destinatarioId);
    Page<Mensagem> findByRemetenteIdAndDestinatarioId(Long remetenteId, Long destinatarioId, Pageable pageable);

}
