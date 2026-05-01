package com.materivas.ecommerce_api.dto;

import java.math.BigDecimal;

public record TopProductoVentasDTO(
        Long idProducto,
        String nombreProducto,
        Long totalUnidadesVendidas,
        BigDecimal ingresosGenerados) {
}