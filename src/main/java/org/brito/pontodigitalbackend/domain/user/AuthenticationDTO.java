package org.brito.pontodigitalbackend.domain.user;

public record AuthenticationDTO(String login, String password, String tipoLogin) {
}
