package com.materivas.ecommerce_api.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.materivas.ecommerce_api.entities.Producto;

public interface ProductoService {
    // CRUD básico
    Producto crearProducto(Producto producto);

    Optional<Producto> actualizarProducto(Long id, Producto producto);

    void eliminarProducto(Long id);

    Optional<Producto> buscarPorId(Long id);

    List<Producto> listarTodos();

    // Métodos específicos
    List<Producto> buscarPorNombre(String nombre);

    List<Producto> buscarPorCategoria(String categoria);

    List<Producto> buscarPorRangoPrecios(BigDecimal min, BigDecimal max);

    boolean reducirStock(Long idProducto, int cantidad);

    List<Producto> buscarProductosActivos(boolean activo);
}
