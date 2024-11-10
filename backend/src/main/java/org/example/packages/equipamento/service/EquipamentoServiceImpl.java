package org.example.packages.equipamento.service;

import lombok.AllArgsConstructor;
import org.example.packages.equipamento.Equipamento;
import org.example.packages.equipamento.EquipamentoRepository;
import org.example.packages.equipamento.payload.EquipamentoCreateRequest;
import org.example.packages.equipamento.payload.EquipamentoResponse;
import org.example.packages.sala.Sala;
import org.example.packages.sala.SalaRepository;
import org.example.packages.tipoequipamento.TipoEquipamento;
import org.example.packages.tipoequipamento.TipoEquipamentoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EquipamentoServiceImpl implements EquipamentoService {

    private EquipamentoRepository equipamentoRepository;
    private SalaRepository salaRepository;
    private TipoEquipamentoRepository tipoEquipamentoRepository;

    @Override
    public Equipamento findByMac(String macAdress) {
        return equipamentoRepository.findByMacAdress(macAdress);
    }

    @Override
    public List<Equipamento> findBySala(Integer salaId) {
        return equipamentoRepository.findBySalaId(salaId);
    }

    @Override
    public void create(EquipamentoCreateRequest e) {
        Sala sala = salaRepository.findById(e.getSalaId()).orElse(null);
        TipoEquipamento tipoEquipamento = tipoEquipamentoRepository.findById(e.getTipoEquipamentoId()).orElse(null);

        Equipamento equipamento = new Equipamento();

        equipamento.setEquipamentoAutorizada(false);
        equipamento.setTipoEquipamento(tipoEquipamento);
        equipamento.setSala(sala);

        equipamentoRepository.save(equipamento);
    }

    @Override
    public EquipamentoResponse findEquipamentoLivreBySala(Integer salaId) {
        List<Equipamento> computadores =  equipamentoRepository.findEquipamentoLivreBySala(salaId);
        List<Equipamento> switchs = equipamentoRepository.findSwitchLivreBySala(salaId);

        EquipamentoResponse equipamentoResponse = new EquipamentoResponse();

        equipamentoResponse.setComputadores(computadores);
        equipamentoResponse.setSwitchs(switchs);

        return equipamentoResponse;
    }
}
