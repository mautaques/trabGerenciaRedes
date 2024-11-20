package org.example.packages.equipamentoporta.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EquipamentoPortaBloqueioRequest {
    List<Integer> ids;
    LocalDateTime dataBloqueio;
}
