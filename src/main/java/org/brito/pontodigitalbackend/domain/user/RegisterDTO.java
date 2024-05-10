package org.brito.pontodigitalbackend.domain.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
