package controller;

import model.UsuarioModel;
import repository.UsuarioRepository;

import java.util.List;

public class UsuarioController {
    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    public String salvar(UsuarioModel usuario) {
        return usuarioRepository.salvar(usuario);
    }

    public List<UsuarioModel> buscarTodos() {
        return usuarioRepository.buscarTodos();
    }

    public UsuarioModel buscarPorId(Long id) {
        return usuarioRepository.buscarPorId(id);
    }

    public String atualizar(UsuarioModel usuario) {
        return usuarioRepository.atualizar(usuario);
    }

    public String remover(Long id) {
        return usuarioRepository.remover(id);
    }
}