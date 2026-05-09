package com.materivas.ecommerce_api.services.implementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.materivas.ecommerce_api.dto.CarritoDTO;
import com.materivas.ecommerce_api.entities.Carrito;
import com.materivas.ecommerce_api.repositories.ICarritoRepository;
import com.materivas.ecommerce_api.services.CarritoService;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private ICarritoRepository carritoRepository;

    public Optional<Carrito> findByIdCarrito(Long id) {
        return carritoRepository.findByIdCarrito(id);
    }

    public Carrito findEntityByIdCarrito(Long id) {
        return carritoRepository.findEntityByIdCarrito(id);
    }

    public boolean existsByUsuarioId(Long idUsuario) {
        return carritoRepository.existsByUsuario_IdUsuario(idUsuario);
    }

    public BigDecimal calcularTotal(Long idCarrito) {
        return carritoRepository.calcularTotal(idCarrito);
    }

    @Override
    public Optional<CarritoDTO> findByUsuarioId(Long idUsuario) {
        List<CarritoDTO> carritos = carritoRepository.findByUsuarioId(idUsuario);
        return carritos.stream().findFirst();
    }
}
