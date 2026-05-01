package com.materivas.ecommerce_api.services;

import java.math.BigDecimal;
import java.util.List;

import com.materivas.ecommerce_api.dto.PedidoItemDTO;
import com.materivas.ecommerce_api.dto.TopProductoVentasDTO;

public interface IPedidoItemService {

    PedidoItemDTO crearItem(PedidoItemDTO item);

    void eliminarItem(Long idItem);

    List<PedidoItemDTO> buscarItemsPorPedido(Long pedidoId);

    List<PedidoItemDTO> buscarItemsPorProducto(Long productoId);

    BigDecimal calcularTotalVentasPorProducto(Long productoId);

    List<TopProductoVentasDTO> obtenerTopProductosMasVendidos();
}