package org.example.packages.equipamentoporta.service;

import org.example.packages.equipamentoporta.EquipamentoPorta;
import org.example.packages.equipamentoporta.payload.EquipamentoPortaCreateRequest;

import java.util.List;

public interface EquipamentoPortaService {

    List<EquipamentoPorta> findEquipamentosPortas(Integer equipamentoId);

    void create(EquipamentoPortaCreateRequest equipamentoPortaCreateRequest);

    void update(EquipamentoPortaCreateRequest equipamentoPortaCreateRequest);
}
