package com.materivas.ecommerce_api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductoDTO {
	private Long idProducto;

	@NotBlank(message = "El SKU es obligatorio")
	private String sku;

	@NotBlank(message = "El nombre no puede estar vacío")
	@Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
	private String nombre;

	private String descripcion;

	@NotNull(message = "El precio es obligatorio")
	@Positive(message = "El precio debe ser mayor a 0")
	private BigDecimal precio;

	@NotNull(message = "El stock es obligatorio")
	@Min(value = 0, message = "El stock no puede ser negativo")
	private int stock;

	@NotBlank(message = "La categoría es obligatoria")
	private String categoria;
}
