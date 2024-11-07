package org.example.packages.usuario;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.AllArgsConstructor;
import org.example.packages.usuario.service.UsuarioService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;


@Component
@GraphQLApi
@AllArgsConstructor
public class UsuarioResolver {

    private UsuarioService usuarioService;

    @GraphQLQuery
    public Usuario usuarioByLoginAndSenha(String login, String senha) {
        return usuarioService.usuarioByLoginAndSenha(login, senha);
    }
}
