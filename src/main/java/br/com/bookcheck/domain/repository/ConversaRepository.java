package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Chat.Conversa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ConversaRepository extends JpaRepository<Conversa, Long> {

    @Query("SELECT c FROM Conversa c JOIN c.participantes p1 JOIN c.participantes p2 " +
            "WHERE p1.id = :userId1 AND p2.id = :userId2 AND SIZE(c.participantes) = 2")
    Optional<Conversa> findConversaByParticipantes(@Param("userId1") Long userId1, @Param("userId2") Long userId2);
}