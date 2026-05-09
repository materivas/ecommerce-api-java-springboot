package com.materivas.ecommerce_api.services;

import java.util.List;
import java.util.Optional;

import com.materivas.ecommerce_api.entities.Usuario;

public interface UsuarioService {

    Usuario crearUsuario(Usuario usuario);

    Optional<Usuario> actualizarUsuario(Long id, Usuario usuario);

    void eliminarUsuario(Long id);

    Optional<Usuario> buscarPorId(Long id);

    List<Usuario> listarTodos();

    Optional<Usuario> buscarPorEmail(String email);

    Optional<Usuario> buscarPorNombreYApellido(String nombre, String apellido);

    boolean existePorEmail(String email);
}