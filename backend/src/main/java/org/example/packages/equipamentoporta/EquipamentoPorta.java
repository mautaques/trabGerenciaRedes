package org.example.packages.equipamentoporta;

import lombok.Getter;
import lombok.Setter;
import org.example.packages.equipamento.Equipamento;
import org.example.packages.sala.Sala;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class EquipamentoPorta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Equipamento equipamentoOrigem;

    @ManyToOne
    private Equipamento equipamentoDestino;

    @ManyToOne
    private Sala sala;

    private Boolean bloqueio;
    private LocalDateTime fimBloqueio;
}
