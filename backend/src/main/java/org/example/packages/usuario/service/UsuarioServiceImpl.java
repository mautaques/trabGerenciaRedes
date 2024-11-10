package org.example.packages.usuario.service;

import lombok.AllArgsConstructor;
import org.example.packages.equipamento.Equipamento;
import org.example.packages.equipamento.service.EquipamentoService;
import org.example.packages.usuario.Usuario;
import org.example.packages.usuario.UsuarioRepository;
import org.example.packages.usuario.payload.UsuarioCreateRequest;
import org.example.packages.usuario.payload.UsuarioResponse;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;
    private EquipamentoService equipamentoService;

    @Override
    public UsuarioResponse usuarioByLoginAndSenha(String login, String senha) throws UnknownHostException, SocketException {

        InetAddress ip = InetAddress.getLocalHost();
        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
        byte[] mac = network.getHardwareAddress();


        String macAddress = "";
        for (int i = 0; i < mac.length; i++) {
            String hexByte = String.format("%02X", mac[i]); // Converte o byte para hexadecimal
            macAddress += hexByte + ((i < mac.length - 1) ? "-" : "");
        }


        Usuario usuario = usuarioRepository.findUsuarioByLoginAndSenha(login, senha);

        if (usuario == null) {
            return null;
        }

        UsuarioResponse usuarioResponse = new UsuarioResponse();

        usuarioResponse.setUsuario(usuario);

        Equipamento equipamento = equipamentoService.findByMac(macAddress);

        if (equipamento == null) {
            return null;
        }

        usuarioResponse.setEquipamento(equipamento);

        return usuarioResponse;
    }

    @Override
    public void createUsuario(UsuarioCreateRequest usuarioCreateRequest) {
        Usuario usuario = new Usuario();

        usuario.setUsuarioChefe(false);
        usuario.setNome(usuarioCreateRequest.getNome());
        usuario.setLogin(usuarioCreateRequest.getLogin());
        usuario.setSenha(usuarioCreateRequest.getSenha());

        usuarioRepository.save(usuario);
    }
}
