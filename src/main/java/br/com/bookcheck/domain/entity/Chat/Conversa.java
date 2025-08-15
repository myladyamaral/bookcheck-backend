package br.com.bookcheck.domain.entity.Chat;

import br.com.bookcheck.domain.entity.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "conversa")
public class Conversa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conversa_seq")
    @SequenceGenerator(name = "conversa_seq", sequenceName = "conversa_seq", allocationSize = 1)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "conversa_participantes",
            joinColumns = @JoinColumn(name = "conversa_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> participantes;

    @OneToMany(mappedBy = "conversa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mensagem> mensagens;

    @Column(nullable = false)
    private LocalDateTime ultimaAtualizacao;
}
