package org.example.packages.equipamento;

import lombok.Getter;
import lombok.Setter;
import org.example.packages.sala.Sala;
import org.example.packages.tipoequipamento.TipoEquipamento;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private TipoEquipamento tipoEquipamento;

    @ManyToOne
    private Sala sala;

    private String macAdress;
    private Boolean equipamentoAutorizada;
}
