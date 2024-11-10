package org.example.packages.equipamento.service;

import org.example.packages.equipamento.Equipamento;
import org.example.packages.equipamento.payload.EquipamentoCreateRequest;
import org.example.packages.equipamento.payload.EquipamentoResponse;

import java.util.List;

public interface EquipamentoService {

    Equipamento findByMac(String macAdress);

    List<Equipamento> findBySala(Integer salaId);

    void create(EquipamentoCreateRequest equipamentoCreateRequest);

    EquipamentoResponse findEquipamentoLivreBySala(Integer salaId);
}
