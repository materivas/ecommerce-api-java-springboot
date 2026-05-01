package com.materivas.ecommerce_api.services.implementation;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.materivas.ecommerce_api.dto.PedidoItemDTO;
import com.materivas.ecommerce_api.dto.TopProductoVentasDTO;
import com.materivas.ecommerce_api.entities.PedidoItem;
import com.materivas.ecommerce_api.repositories.IPedidoItemRepository;

import com.materivas.ecommerce_api.services.IPedidoItemService;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class PedidoItemService implements IPedidoItemService {

    private final IPedidoItemRepository pedidoItemRepository;

    public PedidoItemDTO crearItem(PedidoItemDTO itemDTO) {
        // Acá buscaría el Producto y el Pedido en la BD, armaría la
        // Entidad,
        // la guardo y devuelvo el DTO.
        // Pendiente hasta tener los repositorios listos.
        return null;
    }

    @Override
    public void eliminarItem(@Nullable Long idItem) {
        pedidoItemRepository.deleteById(idItem);
    }

    @Override
    public List<PedidoItemDTO> buscarItemsPorPedido(Long pedidoId) {
        List<PedidoItem> items = pedidoItemRepository.findByPedidoId(pedidoId);

        return items.stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PedidoItemDTO> buscarItemsPorProducto(Long productoId) {
        List<PedidoItem> items = pedidoItemRepository.findByProductoId(productoId);

        return items.stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal calcularTotalVentasPorProducto(Long productoId) {
        // Lo ideal es que esta sumatoria la haga la base de datos con una @Query en el
        // Repositorio
        List<PedidoItem> items = pedidoItemRepository.findByProductoId(productoId);

        return items.stream()
                .map(item -> item.getPrecioUnitario().multiply(new BigDecimal(item.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // --- MÉTODO AUXILIAR PRIVADO PARA MAPEAR ---
    // Este método toma una Entidad y la convierte en el DTO que viaja al
    // Controlador
    private PedidoItemDTO mapearADTO(PedidoItem entidad) {
        PedidoItemDTO dto = new PedidoItemDTO();
        dto.setIdPedidoItem(entidad.getIdPedidoItem());
        dto.setIdProducto(entidad.getProducto().getIdProducto());
        dto.setNombreProducto(entidad.getProducto().getNombre());
        dto.setCantidad(entidad.getCantidad());
        dto.setPrecioUnitario(entidad.getPrecioUnitario());
        return dto;
    }

    @Override
    public List<TopProductoVentasDTO> obtenerTopProductosMasVendidos() {

        return pedidoItemRepository.obtenerTopProductosMasVendidos();
    }
}