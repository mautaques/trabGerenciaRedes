package org.example.packages.equipamentoporta.service;

import lombok.AllArgsConstructor;
import org.example.packages.equipamento.Equipamento;
import org.example.packages.equipamento.EquipamentoRepository;
import org.example.packages.equipamentoporta.EquipamentoPorta;
import org.example.packages.equipamentoporta.EquipamentoPortaRepository;
import org.example.packages.equipamentoporta.payload.EquipamentoPortaBloqueioRequest;
import org.example.packages.equipamentoporta.payload.EquipamentoPortaCreateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EquipamentoPortaServiceImpl implements EquipamentoPortaService {

    private EquipamentoPortaRepository equipamentoPortaRepository;
    private EquipamentoRepository equipamentoRepository;

    @Override
    public List<EquipamentoPorta> findEquipamentosPortas(Integer salaId) {
        return equipamentoPortaRepository.findEquipamentosPorta(salaId);
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
        equipamentoPorta.setNumeroPorta(equipamentoPortaCreateRequest.getNumeroPorta());

        equipamentoPortaRepository.save(equipamentoPorta);
    }

    @Override
    public void bloqueioEquipamentos(EquipamentoPortaBloqueioRequest equipamentoPortaBloqueioRequest) {
        LocalDateTime localDateTime = equipamentoPortaBloqueioRequest.getDataBloqueio();

        if (localDateTime == null) {
            localDateTime = LocalDateTime.now().plusHours(3);
        }

        equipamentoPortaRepository.updateEquipamentos(equipamentoPortaBloqueioRequest.getIds(), localDateTime);
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
        equipamentoPorta.setNumeroPorta(equipamentoPortaCreateRequest.getNumeroPorta());

        equipamentoPortaRepository.save(equipamentoPorta);
    }
}

