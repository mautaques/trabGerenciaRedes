package org.example.packages.usuario.payload;

import lombok.Getter;
import lombok.Setter;
import org.example.packages.equipamento.Equipamento;
import org.example.packages.usuario.Usuario;

@Getter
@Setter
public class UsuarioResponse {
    private Usuario usuario;
    private Equipamento equipamento;
}
