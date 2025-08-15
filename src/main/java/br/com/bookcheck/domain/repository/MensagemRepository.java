package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Chat.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

    List<Mensagem> findByRemetenteIdOrDestinatarioId(Long remetenteId, Long destinatarioId);

    @Query("SELECT m FROM Mensagem m WHERE (m.remetente.id = :user1 AND m.destinatario.id = :user2) " +
            "OR (m.remetente.id = :user2 AND m.destinatario.id = :user1) ORDER BY m.dataEnvio ASC")
    List<Mensagem> find1v1(Long user1, Long user2);
}
