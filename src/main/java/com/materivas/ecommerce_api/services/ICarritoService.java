package com.materivas.ecommerce_api.services;

import java.math.BigDecimal;
import java.util.Optional;

import com.materivas.ecommerce_api.dto.CarritoDTO;
import com.materivas.ecommerce_api.entities.Carrito;

public interface ICarritoService {

    Optional<Carrito> findByIdCarrito(Long id);

    Carrito findEntityByIdCarrito(Long id);

    Optional<CarritoDTO> findByUsuarioId(Long idUsuario);

    boolean existsByUsuarioId(Long idUsuario);

    BigDecimal calcularTotal(Long idCarrito);

}
