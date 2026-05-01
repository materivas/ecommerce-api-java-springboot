package com.materivas.ecommerce_api.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.materivas.ecommerce_api.entities.Pedido;

@Repository ("pedidoRepository")
public interface IPedidoRepository extends JpaRepository <Pedido, Long>{
	
	// Buscar pedidos por usuario
    List<Pedido> findByUsuarioId(Long usuarioId);
    
    // Buscar pedidos entre fechas
    List<Pedido> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    // Buscar pedidos con total mayor a X
    List<Pedido> findByTotalGreaterThan(Double totalMinimo);
    
    // Método personalizado con JPQL para calcular estadísticas
    @Query("SELECT SUM(p.total) FROM Pedido p WHERE p.usuario.id = :usuarioId")
    Double calcularTotalGastadoPorUsuario(@Param("usuarioId") Long usuarioId);
    
    // Buscar pedidos con items específicos (usando JOIN)
    @Query("SELECT DISTINCT p FROM Pedido p JOIN p.items i WHERE i.producto.id = :productoId")
    List<Pedido> findByProductoId(@Param("productoId") Long productoId);
}
