package com.materivas.ecommerce_api.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.materivas.ecommerce_api.entities.Producto;
import com.materivas.ecommerce_api.repositories.IProductoRepository;
import com.materivas.ecommerce_api.services.IProductoService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    @Transactional
    public Producto crearProducto(Producto producto) {
        if (producto.getSku() == null || producto.getNombre() == null) {
            throw new IllegalArgumentException("SKU y Nombre son obligatorios");
        }
        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public Optional<Producto> actualizarProducto(Long id, Producto producto) {
        return productoRepository.findById(id)
                .map(existingProduct -> {
                    producto.setIdProducto(existingProduct.getIdProducto());
                    return productoRepository.save(producto);
                });
    }

    @Override
    @Transactional
    public void eliminarProducto(Long id) {
        productoRepository.findById(id).ifPresent(producto -> {
            producto.setActivo(false); // Borrado lógico
            productoRepository.save(producto);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarPorRangoPrecios(BigDecimal min, BigDecimal max) {
        return productoRepository.findByPrecioBetween(min, max);
    }

    @Override
    @Transactional
    public boolean reducirStock(Long idProducto, int cantidad) {
        return productoRepository.reducirStock(idProducto, cantidad) > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarProductosActivos(boolean activo) {
        return productoRepository.findByActivo(activo);
    }
}