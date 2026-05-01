package com.materivas.ecommerce_api.repositories;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.materivas.ecommerce_api.entities.CarritoItem;

@Repository ("carritoItemRepository")
public interface ICarritoItemRepository extends JpaRepository <CarritoItem, Long>{
	
    // Buscar todos los ítems de un carrito específico
    List<CarritoItem> findByCarrito_IdCarrito(Long idCarrito);

    Optional<CarritoItem> findByCarrito_IdCarritoAndProducto_IdProducto(Long idCarrito, Long idProducto);

    // Eliminar todos los ítems de un carrito (para vaciar el carrito)
    void deleteAllByCarrito_IdCarrito(Long idCarrito);


}
