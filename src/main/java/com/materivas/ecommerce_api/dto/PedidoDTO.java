package com.materivas.ecommerce_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PedidoDTO {
	private Long idPedido;

	private Long idUsuario;

	private LocalDateTime fecha;

	private BigDecimal total;

	private Set<PedidoItemDTO> items = new HashSet<>();

}
