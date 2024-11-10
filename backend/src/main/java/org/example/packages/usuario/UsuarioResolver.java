package org.example.packages.usuario;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.AllArgsConstructor;
import org.example.packages.usuario.payload.UsuarioResponse;
import org.example.packages.usuario.service.UsuarioService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.net.SocketException;
import java.net.UnknownHostException;


@Component
@GraphQLApi
@AllArgsConstructor
public class UsuarioResolver {

    private UsuarioService usuarioService;

    @GraphQLQuery
    public UsuarioResponse usuarioByLoginAndSenha(String login, String senha) throws SocketException, UnknownHostException {
        return usuarioService.usuarioByLoginAndSenha(login, senha);
    }
}
