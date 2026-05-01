package com.materivas.ecommerce_api.repositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.materivas.ecommerce_api.entities.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Long> {

    // ---- Búsquedas básicas ----
    @NonNull
    Optional<Producto> findById(Long idProducto);

    // Buscar por SKU (código único)
    Optional<Producto> findBySku(String sku);

    // Buscar por nombre (ignorando mayúsculas/minúsculas)
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    // Buscar por categoría
    List<Producto> findByCategoria(String categoria);

    // ---- Búsquedas avanzadas ----
    // Productos con stock mayor a 0
    List<Producto> findByStockGreaterThan(int stock);

    // Productos en un rango de precios
    List<Producto> findByPrecioBetween(BigDecimal precioMin, BigDecimal precioMax);

    // Productos creados después de una fecha
    List<Producto> findByFechaCreacionAfter(LocalDateTime fecha);

    // Productos activos/inactivos
    List<Producto> findByActivo(boolean activo);

    // ---- Métodos personalizados con JPQL ----
    // Actualizar stock
    @Modifying
    @Query("UPDATE Producto p SET p.stock = p.stock - :cantidad WHERE p.idProducto = :idProducto AND p.stock >= :cantidad")
    int reducirStock(@Param("idProducto") Long idProducto, @Param("cantidad") int cantidad);

    // Buscar productos por múltiples categorías
    @Query("SELECT p FROM Producto p WHERE p.categoria IN :categorias")
    List<Producto> findByCategorias(@Param("categorias") List<String> categorias);

    // ---- Métodos nativos (para operaciones complejas) ----
    @Query(value = "SELECT * FROM productos WHERE LOWER(nombre) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<Producto> buscarPorPalabraClave(@Param("keyword") String keyword);

}
