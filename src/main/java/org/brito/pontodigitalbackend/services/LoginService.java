package org.brito.pontodigitalbackend.services;

import org.brito.pontodigitalbackend.domain.user.AuthenticationDTO;
import org.brito.pontodigitalbackend.domain.user.LoginResponseDTO;
import org.brito.pontodigitalbackend.domain.user.RegisterDTO;

public interface LoginService {

    LoginResponseDTO login(AuthenticationDTO data);

    String register(RegisterDTO data);
}
