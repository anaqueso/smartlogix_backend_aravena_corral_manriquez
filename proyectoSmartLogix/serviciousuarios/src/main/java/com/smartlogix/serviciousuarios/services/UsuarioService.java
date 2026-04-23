package com.smartlogix.serviciousuarios.services;

import com.smartlogix.serviciousuarios.model.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Usuario guardar(Usuario usuario);
    Optional<Usuario> buscarPorUsername(String username);
    boolean existePorUsername(String username);
}