package com.materivas.ecommerce_api.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.materivas.ecommerce_api.entities.Pedido;
import com.materivas.ecommerce_api.entities.PedidoItem;
import com.materivas.ecommerce_api.entities.Producto;
import com.materivas.ecommerce_api.repositories.IPedidoRepository;
import com.materivas.ecommerce_api.repositories.IProductoRepository;
import com.materivas.ecommerce_api.repositories.IUsuarioRepository;
import com.materivas.ecommerce_api.services.PedidoService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    @Transactional
    public Pedido crearPedido(Pedido pedido) {
        // Validar usuario
        if (!usuarioRepository.existsById(pedido.getUsuario().getIdUsuario())) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // Establecer fecha actual si no viene definida
        if (pedido.getFecha() == null) {
            pedido.setFecha(LocalDateTime.now());
        }

        // Calcular total inicial (puede actualizarse al agregar items)
        pedido.setTotal(0.0);

        return pedidoRepository.save(pedido);
    }

    @Transactional(readOnly = true)
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> buscarPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuario_IdUsuario(usuarioId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> buscarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        return pedidoRepository.findByFechaBetween(inicio, fin);
    }

    @Override
    @Transactional(readOnly = true)
    public Double calcularTotalGastadoPorUsuario(Long usuarioId) {
        return pedidoRepository.calcularTotalGastadoPorUsuario(usuarioId);
    }

    @Override
    @Transactional
    public Pedido agregarItemAPedido(Long pedidoId, Long productoId, int cantidad) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (producto.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }

        PedidoItem item = new PedidoItem();
        item.setPedido(pedido);
        item.setProducto(producto);
        item.setCantidad(cantidad);
        item.setPrecioUnitario(producto.getPrecio());

        // Calcular subtotal (BigDecimal)
        BigDecimal subtotal = producto.getPrecio().multiply(BigDecimal.valueOf(cantidad));

        // Actualizar total del pedido (convertir si es necesario)
        pedido.setTotal(pedido.getTotal() + subtotal.doubleValue());

        pedido.getItems().add(item);
        producto.setStock(producto.getStock() - cantidad);

        productoRepository.save(producto);
        return pedidoRepository.save(pedido);
    }

    @Override
    @Transactional
    public void cancelarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        // Devolver stock de cada item
        pedido.getItems().forEach(item -> {
            Producto producto = item.getProducto();
            producto.setStock(producto.getStock() + item.getCantidad());
            productoRepository.save(producto);
        });

        // Marcar como cancelado (opcional: agregar campo 'estado' a la entidad)
        pedidoRepository.delete(pedido); // O usar borrado lógico
    }
}
