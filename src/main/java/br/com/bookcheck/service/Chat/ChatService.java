package br.com.bookcheck.service.Chat;

import br.com.bookcheck.controller.dto.ConversaDTO;
import br.com.bookcheck.controller.dto.MensagemDTO;
import br.com.bookcheck.controller.dto.UserSummaryDTO;
import br.com.bookcheck.domain.entity.Chat.Mensagem;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import br.com.bookcheck.domain.repository.MensagemRepository;
import br.com.bookcheck.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final MensagemRepository mensagemRepository;
    private final UsuarioRepository usuarioRepository;

    private final DateTimeFormatter isoFormat = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // Lista conversas de um usuário
    @Transactional(readOnly = true)
    public List<ConversaDTO> listarConversas(Long usuarioId) {
        List<Mensagem> msgs = mensagemRepository.findByRemetenteIdOrDestinatarioId(usuarioId, usuarioId);

        Map<Long, List<Mensagem>> grouped = new HashMap<>();
        for (Mensagem m : msgs) {
            Long otherId = m.getRemetente().getId().equals(usuarioId) ? m.getDestinatario().getId() : m.getRemetente().getId();
            grouped.computeIfAbsent(otherId, k -> new ArrayList<>()).add(m);
        }

        List<ConversaDTO> conversas = new ArrayList<>();
        for (Map.Entry<Long, List<Mensagem>> e : grouped.entrySet()) {
            List<Mensagem> convMsgs = e.getValue();
            convMsgs.sort(Comparator.comparing(Mensagem::getDataEnvio));

            Mensagem last = convMsgs.get(convMsgs.size() - 1);
            Usuario other = last.getRemetente().getId().equals(usuarioId) ? last.getRemetente() : last.getDestinatario();
            long unread = convMsgs.stream().filter(m -> !m.isLida() && !m.getRemetente().getId().equals(usuarioId)).count();

            conversas.add(ConversaDTO.builder()
                    .user(UserSummaryDTO.builder()
                            .id(other.getId())
                            .nome(other.getNome())
                            .email(other.getEmail())
                            .descricao(other.getDescricao())
                            .avatarUrl(null)
                            .unread((int) unread)
                            .build())
                    .lastMessage(toDTO(last))
                    .build());
        }

        // Ordena pelo último envio
        conversas.sort((a, b) -> {
            if (a.getLastMessage() == null) return 1;
            if (b.getLastMessage() == null) return -1;
            return b.getLastMessage().getDataEnvio().compareTo(a.getLastMessage().getDataEnvio());
        });

        return conversas;
    }

    // Lista histórico 1-1
    @Transactional(readOnly = true)
    public List<MensagemDTO> listarMensagens(Long usuarioId, Long otherUserId) {
        List<Mensagem> msgs = mensagemRepository.find1v1(usuarioId, otherUserId);
        msgs.sort(Comparator.comparing(Mensagem::getDataEnvio));
        return msgs.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Enviar mensagem
    @Transactional
    public MensagemDTO enviarMensagem(Long remetenteId, Long destinatarioId, String conteudo) {
        Usuario remetente = usuarioRepository.findById(remetenteId).orElseThrow();
        Usuario destinatario = usuarioRepository.findById(destinatarioId).orElseThrow();

        Mensagem msg = Mensagem.builder()
                .remetente(remetente)
                .destinatario(destinatario)
                .conteudo(conteudo)
                .dataEnvio(java.time.LocalDateTime.now())
                .lida(false)
                .build();

        mensagemRepository.save(msg);
        return toDTO(msg);
    }

    private MensagemDTO toDTO(Mensagem m) {
        return MensagemDTO.builder()
                .id(m.getId())
                .conteudo(m.getConteudo())
                .dataEnvio(m.getDataEnvio().format(isoFormat))
                .lida(m.isLida())
                .remetente(UserSummaryDTO.builder()
                        .id(m.getRemetente().getId())
                        .nome(m.getRemetente().getNome())
                        .email(m.getRemetente().getEmail())
                        .build())
                .destinatario(UserSummaryDTO.builder()
                        .id(m.getDestinatario().getId())
                        .nome(m.getDestinatario().getNome())
                        .email(m.getDestinatario().getEmail())
                        .build())
                .build();
    }
}
