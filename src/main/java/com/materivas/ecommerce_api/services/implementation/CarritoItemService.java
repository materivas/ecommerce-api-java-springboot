package com.materivas.ecommerce_api.services.implementation;

import com.materivas.ecommerce_api.entities.CarritoItem;
import com.materivas.ecommerce_api.repositories.ICarritoItemRepository;
import com.materivas.ecommerce_api.services.ICarritoItemService;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoItemService implements ICarritoItemService {

    @Autowired
    private ICarritoItemRepository carritoItemRepository;

    // Obtener todos los ítems de un carrito
    @Override
    public List<CarritoItem> findByCarrito_IdCarrito(Long idCarrito) {
        return carritoItemRepository.findByCarrito_IdCarrito(idCarrito);
    }

    // Buscar un ítem por carrito y producto
    @Override
    public Optional<CarritoItem> obtenerItemDelCarrito(Long idCarrito, Long idProducto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerItemDelCarrito'");
    }

    // Eliminar todos los ítems de un carrito
    @Override
    public void deleteAllByCarrito_IdCarrito(Long idCarrito) {
        carritoItemRepository.deleteAllByCarrito_IdCarrito(idCarrito);
    }

}