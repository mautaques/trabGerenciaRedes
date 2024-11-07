package org.example.packages.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("select u from Usuario u "
            + "where u.login = :login "
            + "and u.senha = :senha")
    Usuario findUsuarioByLoginAndSenha(String login, String senha);
}
