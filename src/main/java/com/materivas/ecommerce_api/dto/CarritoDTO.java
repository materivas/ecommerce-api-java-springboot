package com.materivas.ecommerce_api.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarritoDTO {
    private Long idCarrito;

    private Long idUsuario;

    private Set<CarritoItemDTO> items = new HashSet<>();

    private BigDecimal total;
}
