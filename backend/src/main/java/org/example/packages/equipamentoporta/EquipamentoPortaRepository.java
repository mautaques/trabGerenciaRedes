package org.example.packages.equipamentoporta;

import org.example.packages.equipamento.Equipamento;
import org.example.packages.sala.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipamentoPortaRepository extends JpaRepository<EquipamentoPorta, Integer> {

    List<EquipamentoPorta> findBySala(Sala sala);

    @Query("select e from EquipamentoPorta e " +
            "where e.equipamentoOrigem.id = :equipamentoId")
    EquipamentoPorta findEquipamentoDestino(Integer equipamentoId);

    @Query("select e from EquipamentoPorta e " +
            "where e.equipamentoOrigem.id = :equipamentoId")
    List<EquipamentoPorta> findEquipamentosPorta(Integer equipamentoId);
}
