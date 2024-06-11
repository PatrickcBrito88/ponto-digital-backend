package org.brito.pontodigitalbackend.repositories;

import org.brito.pontodigitalbackend.domain.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String login);

    Usuario findUsuarioByLogin(String login);

    Optional<Usuario> findById(Long id);

}
