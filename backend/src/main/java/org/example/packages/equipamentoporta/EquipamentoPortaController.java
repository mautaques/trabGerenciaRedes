package org.example.packages.equipamentoporta;

import lombok.AllArgsConstructor;
import org.example.packages.equipamento.EquipamentoRepository;
import org.example.packages.equipamento.payload.EquipamentoCreateRequest;
import org.example.packages.equipamento.service.EquipamentoService;
import org.example.packages.equipamentoporta.payload.EquipamentoPortaBloqueioRequest;
import org.example.packages.equipamentoporta.payload.EquipamentoPortaCreateRequest;
import org.example.packages.equipamentoporta.service.EquipamentoPortaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("equipamento_porta")
@AllArgsConstructor
public class EquipamentoPortaController {
    private EquipamentoPortaService equipamentoPortaService;
    private EquipamentoPortaRepository equipamentoPortaRepository;

    @PostMapping
    public void create(@RequestBody EquipamentoPortaCreateRequest equipamentoPortaCreateRequest) {
        equipamentoPortaService.create(equipamentoPortaCreateRequest);
    }

    @PatchMapping
    public void update(@RequestBody EquipamentoPortaCreateRequest equipamentoPortaCreateRequest) {
        equipamentoPortaService.update(equipamentoPortaCreateRequest);
    }

    @PatchMapping("desabilitar")
    public void desabilitarPortas(@RequestBody EquipamentoPortaBloqueioRequest equipamentoPortaCreateRequest) {
        equipamentoPortaService.bloqueioEquipamentos(equipamentoPortaCreateRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        EquipamentoPorta equipamentoPorta = equipamentoPortaRepository.findById(id).orElse(null);

        equipamentoPortaRepository.delete(equipamentoPorta);
    }
}
