package org.example.packages.equipamento.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipamentoCreateRequest {
    private Integer salaId;
    private Integer tipoEquipamentoId;
}
