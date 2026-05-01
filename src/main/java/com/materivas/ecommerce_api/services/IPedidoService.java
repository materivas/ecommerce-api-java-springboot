package com.materivas.ecommerce_api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.materivas.ecommerce_api.entities.Pedido;

public interface IPedidoService {
    Pedido crearPedido(Pedido pedido);
    Optional<Pedido> buscarPorId(Long id);
    List<Pedido> buscarPorUsuario(Long usuarioId);
    List<Pedido> buscarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin);
    Double calcularTotalGastadoPorUsuario(Long usuarioId);
    Pedido agregarItemAPedido(Long pedidoId, Long productoId, int cantidad);
    void cancelarPedido(Long pedidoId);
}
