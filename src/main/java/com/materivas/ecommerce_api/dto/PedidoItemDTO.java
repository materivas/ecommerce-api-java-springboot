package com.materivas.ecommerce_api.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PedidoItemDTO {

    private Long idPedidoItem;

    private Long idPedido;

    private Long idProducto;

    private String nombreProducto;

    private int cantidad;

    private BigDecimal precioUnitario;

}
