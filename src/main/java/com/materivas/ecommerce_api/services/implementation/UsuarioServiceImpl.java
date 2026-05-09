package com.materivas.ecommerce_api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.materivas.ecommerce_api.entities.Usuario;
import com.materivas.ecommerce_api.repositories.IUsuarioRepository;
import com.materivas.ecommerce_api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Transactional
    public Usuario crearUsuario(Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getApellido() == null
                || usuario.getEmail() == null || usuario.getPassword() == null) {
            throw new IllegalArgumentException("Nombre, apellido, email y password son obligatorios");
        }

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El email ya se encuentra registrado");
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Optional<Usuario> actualizarUsuario(Long id, Usuario usuario) {
        return usuarioRepository.findById(id)
                .map(existingUser -> {
                    usuario.setIdUsuario(existingUser.getIdUsuario());
                    return usuarioRepository.save(usuario);
                });
    }

    @Override
    @Transactional
    public void eliminarUsuario(Long id) {
        usuarioRepository.findById(id).ifPresent(usuarioRepository::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorNombreYApellido(String nombre, String apellido) {
        return usuarioRepository.findByNombreAndApellido(nombre, apellido);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}
