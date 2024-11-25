package org.example.packages.equipamentoporta.service;

import org.example.packages.equipamentoporta.EquipamentoPorta;
import org.example.packages.equipamentoporta.payload.EquipamentoPortaBloqueioRequest;
import org.example.packages.equipamentoporta.payload.EquipamentoPortaCreateRequest;

import java.util.List;

public interface EquipamentoPortaService {

    List<EquipamentoPorta> findEquipamentosPortas(Integer salaId);

    void create(EquipamentoPortaCreateRequest equipamentoPortaCreateRequest);

    void update(EquipamentoPortaCreateRequest equipamentoPortaCreateRequest);

    void bloqueioEquipamentos(EquipamentoPortaBloqueioRequest equipamentoPortaBloqueioRequest);

    void desloqueioEquipamento(EquipamentoPortaBloqueioRequest equipamentoPortaBloqueioRequest);
}
