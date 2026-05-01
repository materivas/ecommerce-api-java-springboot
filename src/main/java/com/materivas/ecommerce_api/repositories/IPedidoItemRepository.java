package com.materivas.ecommerce_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.materivas.ecommerce_api.dto.TopProductoVentasDTO;
import com.materivas.ecommerce_api.entities.PedidoItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;

@Repository("pedidoItemRepository")
public interface IPedidoItemRepository extends JpaRepository<PedidoItem, Long> {

    List<PedidoItem> findByPedidoId(Long pedidoId);

    List<PedidoItem> findByProductoId(Long productoId);

    List<PedidoItem> findByCantidadGreaterThan(int cantidad);

    List<PedidoItem> findByPrecioUnitarioGreaterThan(BigDecimal precioMinimo);

    @Query("SELECT SUM(pi.precioUnitario * pi.cantidad) FROM PedidoItem pi WHERE pi.producto.id = :productoId")
    BigDecimal calcularTotalVentasPorProducto(@Param("productoId") Long productoId);

    @Query("""
                SELECT new com.materivas.ecommerce_api.dto.TopProductoVentasDTO(
                    p.idProducto,
                    p.nombre,
                    SUM(pi.cantidad),
                    SUM(pi.precioUnitario * pi.cantidad)
                )
                FROM PedidoItem pi
                JOIN pi.producto p
                GROUP BY p.idProducto, p.nombre
                ORDER BY SUM(pi.cantidad) DESC
            """)
    List<TopProductoVentasDTO> obtenerTopProductosMasVendidos();
}
