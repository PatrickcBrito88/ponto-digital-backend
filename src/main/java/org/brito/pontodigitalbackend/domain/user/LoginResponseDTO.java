package org.brito.pontodigitalbackend.domain.user;

public record LoginResponseDTO(Long id, String login, UserRole role, String nome, String token) {
}
