package org.example.packages.usuario.service;

import org.example.packages.usuario.Usuario;
import org.example.packages.usuario.payload.UsuarioCreateRequest;

public interface UsuarioService {

    Usuario usuarioByLoginAndSenha(String login, String senha);

    void createUsuario(UsuarioCreateRequest usuarioCreateRequest);
}
