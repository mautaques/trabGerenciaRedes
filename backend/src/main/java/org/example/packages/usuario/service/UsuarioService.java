package org.example.packages.usuario.service;

import org.example.packages.usuario.Usuario;
import org.example.packages.usuario.payload.UsuarioCreateRequest;
import org.example.packages.usuario.payload.UsuarioResponse;

import java.net.SocketException;
import java.net.UnknownHostException;

public interface UsuarioService {

    UsuarioResponse usuarioByLoginAndSenha(String login, String senha) throws UnknownHostException, SocketException;

    void createUsuario(UsuarioCreateRequest usuarioCreateRequest);
}
