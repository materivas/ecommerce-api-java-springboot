package com.materivas.ecommerce_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Usuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	
    @Column(nullable = false)
	private String nombre;
    
    @Column(nullable = false)
	private String apellido;
    
    @Column(unique = true, nullable = false)
	private String email;
    
    @Column(nullable = false)
	private String password;
	
    @Enumerated(EnumType.STRING)
    private Rol rol;
}
