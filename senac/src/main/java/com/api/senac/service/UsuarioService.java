package com.api.senac.service;

import com.api.senac.data.UsuarioEntity;
import com.api.senac.data.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioEntity> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioEntity buscarUsuarioPorUsuario(String usuario) {
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(usuario);
        return usuarioOptional.orElse(null);
    }

    public boolean validarUsuario(String usuario, String senha) {
        UsuarioEntity usuarioEntity = buscarUsuarioPorUsuario(usuario);
        return usuarioEntity.getSenha().equals(senha);
    }

    public void salvarUsuario(UsuarioEntity usuario) {
        usuarioRepository.save(usuario);
    }
}
