package org.example.packages.equipamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer> {

    Equipamento findByMacAdress(String macAdress);

    List<Equipamento> findBySalaId(Integer salaId);

    @Query("select e from Equipamento e "
            + "left join EquipamentoPorta eq on eq.equipamentoOrigem.id = e.id or eq.equipamentoDestino.id = e.id "
            + "where e.sala.id = :salaId  "
            + "and e.tipoEquipamento.id = 1 "
            + "and eq is null")
    List<Equipamento> findEquipamentoLivreBySala(Integer salaId);

    @Query("select e from Equipamento e "
            + "left join EquipamentoPorta eq on eq.equipamentoOrigem.id = e.id or eq.equipamentoDestino.id = e.id " +
            "where e.sala.id = :salaId "
            + "and e.tipoEquipamento.id = 2 "
            + "group by e "
            + "having count(eq) < 22")
    List<Equipamento> findSwitchLivreBySala(Integer salaId);
}
