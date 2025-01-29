package controller;

import model.UsuarioModel;
import repository.UsuarioRepository;

import java.sql.SQLException;

public class UsuarioController {
    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    public String salvar(UsuarioModel usuario) throws SQLException {
        return usuarioRepository.salvar(usuario);
    }
}