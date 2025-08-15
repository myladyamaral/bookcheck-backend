package br.com.bookcheck.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSummaryDTO {
    private Long id;
    private String nome;
    private String email;
    private String avatarUrl;
    private String descricao;
    private Integer unread; // mensagens não lidas
}
