package org.example.packages.usuario;

import org.example.packages.usuario.payload.UsuarioCreateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
public class UsuarioController {


    @PostMapping
    public void create(@RequestBody UsuarioCreateRequest usuarioCreateRequest) {

    }
}
