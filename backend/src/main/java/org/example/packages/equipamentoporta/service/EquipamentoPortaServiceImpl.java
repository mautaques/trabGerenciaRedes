package org.example.packages.equipamentoporta.service;

import lombok.AllArgsConstructor;
import org.example.packages.equipamento.Equipamento;
import org.example.packages.equipamento.EquipamentoRepository;
import org.example.packages.equipamentoporta.EquipamentoPorta;
import org.example.packages.equipamentoporta.EquipamentoPortaRepository;
import org.example.packages.equipamentoporta.payload.EquipamentoPortaCreateRequest;
import org.example.packages.sala.Sala;
import org.example.packages.sala.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EquipamentoPortaServiceImpl implements EquipamentoPortaService {

    private EquipamentoPortaRepository equipamentoPortaRepository;
    private EquipamentoRepository equipamentoRepository;

    @Override
    public List<EquipamentoPorta> findEquipamentosPortas(Integer equipamentoId) {
        EquipamentoPorta equipamentoDestino = equipamentoPortaRepository.findEquipamentoDestino(equipamentoId);

        List<EquipamentoPorta> equipamentoPortas = equipamentoPortaRepository.findEquipamentosPorta(equipamentoDestino.getEquipamentoDestino().getId());

        equipamentoPortas.add(equipamentoDestino);

        return equipamentoPortas;
    }

    @Override
    public void create(EquipamentoPortaCreateRequest equipamentoPortaCreateRequest) {
        EquipamentoPorta equipamentoPorta = new EquipamentoPorta();

        Equipamento equipamentoOrigem = equipamentoRepository.findById(equipamentoPortaCreateRequest.getEquipamentoOrigemId()).orElse(null);
        Equipamento equipamentoDestino = equipamentoRepository.findById(equipamentoPortaCreateRequest.getEquipamentoDestinoId()).orElse(null);

        equipamentoPorta.setEquipamentoOrigem(equipamentoOrigem);
        equipamentoPorta.setEquipamentoDestino(equipamentoDestino);
        equipamentoPorta.setSala(equipamentoDestino.getSala());
        equipamentoPorta.setFimBloqueio(equipamentoPortaCreateRequest.getDataBloqueio());

        equipamentoPortaRepository.save(equipamentoPorta);
    }

    @Override
    public void update(EquipamentoPortaCreateRequest equipamentoPortaCreateRequest) {
        EquipamentoPorta equipamentoPorta = equipamentoPortaRepository.findById(equipamentoPortaCreateRequest.getId()).orElse(null);

        Equipamento equipamentoOrigem = equipamentoRepository.findById(equipamentoPortaCreateRequest.getEquipamentoOrigemId()).orElse(null);
        Equipamento equipamentoDestino = equipamentoRepository.findById(equipamentoPortaCreateRequest.getEquipamentoDestinoId()).orElse(null);

        equipamentoPorta.setEquipamentoOrigem(equipamentoOrigem);
        equipamentoPorta.setEquipamentoDestino(equipamentoDestino);
        equipamentoPorta.setSala(equipamentoDestino.getSala());
        equipamentoPorta.setFimBloqueio(equipamentoPortaCreateRequest.getDataBloqueio());

        equipamentoPortaRepository.save(equipamentoPorta);
    }
}

