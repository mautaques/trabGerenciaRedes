package org.example.packages.equipamentoporta.service;

import lombok.AllArgsConstructor;
import org.example.packages.equipamento.Equipamento;
import org.example.packages.equipamento.EquipamentoRepository;
import org.example.packages.equipamentoporta.EquipamentoPorta;
import org.example.packages.equipamentoporta.EquipamentoPortaRepository;
import org.example.packages.equipamentoporta.payload.EquipamentoPortaBloqueioRequest;
import org.example.packages.equipamentoporta.payload.EquipamentoPortaCreateRequest;
import org.example.packages.snmp.SNMPService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EquipamentoPortaServiceImpl implements EquipamentoPortaService {

    private EquipamentoPortaRepository equipamentoPortaRepository;
    private EquipamentoRepository equipamentoRepository;
    private SNMPService snmpService;

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

        List<EquipamentoPorta> equipamentoPortas = equipamentoPortaRepository
                .findEquipamentoByIds(equipamentoPortaBloqueioRequest.getIds());


        for(EquipamentoPorta ep : equipamentoPortas) {
            ep.setFimBloqueio(localDateTime);

            equipamentoPortaRepository.save(ep);
            snmpService.setPortState("10.90.90.90", ep.getNumeroPorta(), false);
        }
    }

    @Override
    public void desloqueioEquipamento(EquipamentoPortaBloqueioRequest equipamentoPortaBloqueioRequest) {
        List<EquipamentoPorta> equipamentoPortas = equipamentoPortaRepository
                .findEquipamentoByIds(equipamentoPortaBloqueioRequest.getIds());


        for(EquipamentoPorta ep : equipamentoPortas) {
            ep.setFimBloqueio(null);

            equipamentoPortaRepository.save(ep);
            snmpService.setPortState("10.90.90.90", ep.getNumeroPorta(), true);
        }
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

    @Scheduled(cron = "0 * * * * *")
    public void executeTaskWithCron() {
        List<EquipamentoPorta> equipamentoPortas = equipamentoPortaRepository.findByData(LocalDateTime.now());

        for(EquipamentoPorta ep : equipamentoPortas) {
            ep.setFimBloqueio(null);

            equipamentoPortaRepository.save(ep);
            snmpService.setPortState("10.90.90.90", ep.getNumeroPorta(), true);
        }
    }
}

