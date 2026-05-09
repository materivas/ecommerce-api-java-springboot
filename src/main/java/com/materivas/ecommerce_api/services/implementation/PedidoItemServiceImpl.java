package com.materivas.ecommerce_api.services.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.materivas.ecommerce_api.dto.PedidoItemDTO;
import com.materivas.ecommerce_api.dto.TopProductoVentasDTO;
import com.materivas.ecommerce_api.entities.Pedido;
import com.materivas.ecommerce_api.entities.PedidoItem;
import com.materivas.ecommerce_api.entities.Producto;
import com.materivas.ecommerce_api.repositories.IPedidoItemRepository;
import com.materivas.ecommerce_api.repositories.IPedidoRepository;
import com.materivas.ecommerce_api.repositories.IProductoRepository;

import com.materivas.ecommerce_api.services.PedidoItemService;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoItemServiceImpl implements PedidoItemService {

    private final IPedidoItemRepository pedidoItemRepository;
    private final IPedidoRepository pedidoRepository;
    private final IProductoRepository productoRepository;

    @Override
    @Transactional
    public PedidoItemDTO crearItem(PedidoItemDTO itemDTO) {
        Pedido pedido = pedidoRepository.findById(itemDTO.getIdPedido())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        Producto producto = productoRepository.findById(itemDTO.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (producto.getStock() < itemDTO.getCantidad()) {
            throw new RuntimeException("Stock insuficiente");
        }

        PedidoItem item = new PedidoItem();
        item.setPedido(pedido);
        item.setProducto(producto);
        item.setCantidad(itemDTO.getCantidad());
        item.setPrecioUnitario(producto.getPrecio());

        BigDecimal subtotal = producto.getPrecio().multiply(BigDecimal.valueOf(itemDTO.getCantidad()));
        pedido.setTotal(pedido.getTotal() + subtotal.doubleValue());
        producto.setStock(producto.getStock() - itemDTO.getCantidad());

        productoRepository.save(producto);
        pedidoRepository.save(pedido);
        PedidoItem itemGuardado = pedidoItemRepository.save(item);
        return mapearADTO(itemGuardado);
    }

    @Override
    @Transactional
    public void eliminarItem(Long idItem) {
        if (idItem == null) {
            throw new IllegalArgumentException("El id del item es obligatorio");
        }
        pedidoItemRepository.deleteById(idItem);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoItemDTO> buscarItemsPorPedido(Long pedidoId) {
        List<PedidoItem> items = pedidoItemRepository.findByPedido_IdPedido(pedidoId);

        return items.stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoItemDTO> buscarItemsPorProducto(Long productoId) {
        List<PedidoItem> items = pedidoItemRepository.findByProducto_IdProducto(productoId);

        return items.stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal calcularTotalVentasPorProducto(Long productoId) {
        // Lo ideal es que esta sumatoria la haga la base de datos con una @Query en el
        // Repositorio
        List<PedidoItem> items = pedidoItemRepository.findByProducto_IdProducto(productoId);

        return items.stream()
                .map(item -> item.getPrecioUnitario().multiply(BigDecimal.valueOf(item.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // --- MÉTODO AUXILIAR PRIVADO PARA MAPEAR ---
    // Este método toma una Entidad y la convierte en el DTO que viaja al
    // Controlador
    private PedidoItemDTO mapearADTO(PedidoItem entidad) {
        PedidoItemDTO dto = new PedidoItemDTO();
        dto.setIdPedidoItem(entidad.getIdPedidoItem());
        dto.setIdPedido(entidad.getPedido().getIdPedido());
        dto.setIdProducto(entidad.getProducto().getIdProducto());
        dto.setNombreProducto(entidad.getProducto().getNombre());
        dto.setCantidad(entidad.getCantidad());
        dto.setPrecioUnitario(entidad.getPrecioUnitario());
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TopProductoVentasDTO> obtenerTopProductosMasVendidos() {

        return pedidoItemRepository.obtenerTopProductosMasVendidos();
    }
}
