package org.example.packages.usuario.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCreateRequest {
    private String login;
    private String senha;
    private String nome;
}
