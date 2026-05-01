package com.materivas.ecommerce_api.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.materivas.ecommerce_api.dto.CarritoDTO;
import com.materivas.ecommerce_api.entities.Carrito;

@Repository("carritoRepository")
public interface ICarritoRepository extends JpaRepository<Carrito, Long> {

    Optional<Carrito> findByIdCarrito(Long id);

    Carrito findEntityByIdCarrito(Long id);

    @Query("SELECT new com.materivas.ecommerce_api.dto.CarritoDTO(c.idCarrito, c.usuario.id) " +
            "FROM Carrito c WHERE c.usuario.id = :idUsuario")
    List<CarritoDTO> findByUsuarioId(@Param("idUsuario") Long idUsuario);

    // Verificar si existe carrito para un usuario
    boolean existsByUsuario_IdUsuario(Long idUsuario);

    // Calcular total del carrito
    @Query("SELECT COALESCE(SUM(ci.cantidad * p.precio), 0) " +
            "FROM CarritoItem ci JOIN ci.producto p " +
            "WHERE ci.carrito.idCarrito = :id")
    BigDecimal calcularTotal(@Param("id") Long idCarrito);

}
