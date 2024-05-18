package org.brito.pontodigitalbackend.dtos;

import lombok.Setter;
import org.brito.pontodigitalbackend.domain.user.UserRole;

@Setter
public class UsuarioLogadoDTO {

    private Long id;
    private String login;
    private UserRole role;
    private String nome;
    private String token;
}
