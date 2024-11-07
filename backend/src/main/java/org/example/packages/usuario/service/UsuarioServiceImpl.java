package org.example.packages.usuario.service;

import lombok.AllArgsConstructor;
import org.example.packages.usuario.Usuario;
import org.example.packages.usuario.UsuarioRepository;
import org.example.packages.usuario.payload.UsuarioCreateRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario usuarioByLoginAndSenha(String login, String senha) {

        return usuarioRepository.findUsuarioByLoginAndSenha(login, senha);
    }

    @Override
    public void createUsuario(UsuarioCreateRequest usuarioCreateRequest) {
        Usuario usuario = new Usuario();

        usuario.setUsuarioChefe(false);
        usuario.setNome(usuarioCreateRequest.getNome());
        usuario.setLogin(usuarioCreateRequest.getLogin());
        usuario.setSenha(usuario.getSenha());

        usuarioRepository.save(usuario);
    }
}
