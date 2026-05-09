package com.materivas.ecommerce_api.services;

import java.util.List;
import java.util.Optional;

import com.materivas.ecommerce_api.entities.CarritoItem;

public interface CarritoItemService {

    List<CarritoItem> findByCarrito_IdCarrito(Long idCarrito);

    Optional<CarritoItem> obtenerItemDelCarrito(Long idCarrito, Long idProducto);

    void deleteAllByCarrito_IdCarrito(Long idCarrito);

}
