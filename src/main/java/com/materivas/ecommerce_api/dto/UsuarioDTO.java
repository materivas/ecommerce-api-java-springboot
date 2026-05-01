package com.materivas.ecommerce_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.materivas.ecommerce_api.entities.Rol;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {

	private Long idUsuario;

	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;

	@NotBlank(message = "El apellido es obligatorio")
	private String apellido;

	@Email(message = "Debe tener un formato de correo válido")
	@NotBlank(message = "El email es obligatorio")
	private String email;

	@NotBlank(message = "La contraseña es obligatoria")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@NotNull(message = "El rol no puede ser nulo")
	private Rol rol;
}
