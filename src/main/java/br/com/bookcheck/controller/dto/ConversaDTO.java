package br.com.bookcheck.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConversaDTO {
    private UserSummaryDTO user; // pessoa com quem estou conversando
    private MensagemDTO lastMessage;
}
