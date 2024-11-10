package org.example.packages.equipamento.payload;

import lombok.Getter;
import lombok.Setter;
import org.example.packages.equipamento.Equipamento;

import java.util.List;

@Getter
@Setter
public class EquipamentoResponse {
    List<Equipamento> computadores;
    List<Equipamento> switchs;
}
