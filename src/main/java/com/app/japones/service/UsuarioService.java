package com.app.japones.service;

import com.app.japones.model.Usuario;
import com.app.japones.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario) {
        if (repository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        return repository.save(usuario);
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario buscarPorId(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Usuario login(String email, String senha) {
        return repository.findAll().stream()
                .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));
    } // para testes, depois hashear senha *


}
