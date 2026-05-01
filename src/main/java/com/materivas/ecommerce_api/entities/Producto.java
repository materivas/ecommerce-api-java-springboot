package com.materivas.ecommerce_api.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProducto;

	@Column(length = 50, nullable = false, unique = true)
	private String sku; // Código único (ej: "PROD-001")

	@Column(length = 100, nullable = false)
	private String nombre;

	@Column(columnDefinition = "TEXT") // Para descripciones largas
	private String descripcion;

	@Column(nullable = false, precision = 10, scale = 2) // Ej: 99999999.99
	private BigDecimal precio;

	@Column(nullable = false)
	private int stock;

	@Column(nullable = false)
	private String categoria; // Ej: "Electrónicos", "Ropa"

	@CreationTimestamp // Hibernate auto-genera la fecha
	@Column(name = "fecha_creacion", updatable = false)
	private LocalDateTime fechaCreacion;

	@UpdateTimestamp
	@Column(name = "fecha_actualizacion")
	private LocalDateTime fechaActualizacion;

	@Column(nullable = false)
	private boolean activo = true; // Para borrado lógico

}
