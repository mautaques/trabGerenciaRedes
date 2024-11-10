package org.example.packages.equipamentoporta;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.AllArgsConstructor;
import org.example.packages.equipamentoporta.service.EquipamentoPortaService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@GraphQLApi
@AllArgsConstructor
public class EquipamentoPortaResolver {
    private EquipamentoPortaService equipamentoPortaService;

    @GraphQLQuery
    public List<EquipamentoPorta> equipamentosPortas(Integer equipamentoId) {
        return equipamentoPortaService.findEquipamentosPortas(equipamentoId);
    }
}
