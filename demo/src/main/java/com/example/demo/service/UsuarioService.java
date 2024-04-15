package com.example.demo.service;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class UsuarioService {
    @Inject
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listar() {
        return usuarioRepository.listarTodos();
    }

    public void cadastrar(Usuario usuario) {
        usuarioRepository.cadastrar(usuario);
    }

    public void atualizar(Usuario usuario) {
        usuarioRepository.atualizar(usuario);
    }

    public void excluir(Usuario usuario) {
        usuarioRepository.excluir(usuario);
    }

    public Usuario buscarPorID(Integer id) {
        return usuarioRepository.buscarPorID(id);
    }
}
