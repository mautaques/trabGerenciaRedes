package org.example.packages.sala;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.AllArgsConstructor;
import org.example.packages.sala.service.SalaService;
import org.example.packages.usuario.payload.UsuarioResponse;
import org.springframework.stereotype.Component;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

@Component
@GraphQLApi
@AllArgsConstructor
public class SalaResolver {
    private SalaService salaService;

    @GraphQLQuery
    public List<Sala> salas() {
        return salaService.salas();
    }
}
