package org.example.packages.equipamentoporta.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EquipamentoPortaCreateRequest {
    Integer id;
    Integer equipamentoOrigemId;
    Integer equipamentoDestinoId;
    LocalDateTime dataBloqueio;
}
