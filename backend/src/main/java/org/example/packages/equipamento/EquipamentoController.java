package org.example.packages.equipamento;

import lombok.AllArgsConstructor;
import org.example.packages.equipamento.payload.EquipamentoCreateRequest;
import org.example.packages.equipamento.service.EquipamentoService;
import org.example.packages.usuario.payload.UsuarioCreateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("equipamento")
@AllArgsConstructor
public class EquipamentoController {
    private EquipamentoService equipamentoService;

    @PostMapping
    public void create(@RequestBody EquipamentoCreateRequest equipamentoCreateRequest) {
        equipamentoService.create(equipamentoCreateRequest);
    }
}
