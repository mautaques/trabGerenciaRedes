package org.example.packages.equipamento;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.AllArgsConstructor;
import org.example.packages.equipamento.payload.EquipamentoResponse;
import org.example.packages.equipamento.service.EquipamentoService;
import org.example.packages.equipamentoporta.EquipamentoPorta;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@GraphQLApi
@AllArgsConstructor
public class EquipamentoResolver {
    private EquipamentoService equipamentoService;

    @GraphQLQuery
    public List<Equipamento> equipamentosBySala(Integer salaId) {
        return equipamentoService.findBySala(salaId);
    }


    @GraphQLQuery
    public EquipamentoResponse equipamentosBySalaLivres(Integer salaId) {
        return equipamentoService.findEquipamentoLivreBySala(salaId);
    }
}
