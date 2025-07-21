package br.com.bookcheck.controller.dto.response;

public record AuthenticationResponseDto(String token, String refreshToken) { }
