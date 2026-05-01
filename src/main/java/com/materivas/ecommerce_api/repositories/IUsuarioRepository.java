package com.materivas.ecommerce_api.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.materivas.ecommerce_api.entities.Usuario;

@Repository ("usuarioRepository")
public interface IUsuarioRepository extends JpaRepository <Usuario, Serializable>{
	
	public abstract Optional<Usuario> findById(Long id);
	
	public abstract Optional<Usuario> findByNombreAndApellido(String nombre, String apellido);

	Optional<Usuario> findByEmail(String email);

	boolean existsById(Long id);
	
	boolean existsByEmail(String email);
}
