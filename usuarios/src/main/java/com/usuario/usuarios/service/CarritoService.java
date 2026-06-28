package com.usuario.usuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario.usuarios.dto.CarritoDTO;
import com.usuario.usuarios.model.Carrito;
import com.usuario.usuarios.repository.CarritoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<CarritoDTO> listarCarritos(){
        return carritoRepository.findAll().stream().map(this::convertirCarritoDTO).toList();
    }

    public Carrito guardarCarrito(Carrito carrito){
        return carritoRepository.save(carrito);
    }

    public CarritoDTO buscarCarrito(Long id_carrito){
        Carrito carrito = carritoRepository.findById(id_carrito).orElseThrow(() -> new RuntimeException("No se ha encontrado el carrito con la ID " + id_carrito));
        return convertirCarritoDTO(carrito);
    }

    public Carrito editarCarrito(Long id_carrito, Carrito carrito1){
        Carrito carrito = carritoRepository.findById(id_carrito).orElseThrow(() -> new RuntimeException("No se ha encontrado el carrito con la ID " + id_carrito));
        if(carrito1.getCantidad() !=  null){
            carrito.setCantidad(carrito1.getCantidad());
        }
        return carritoRepository.save(carrito);
    }

    public String eliminarCarrito(Long id_carrito){
        try{
            Carrito carrito = carritoRepository.findById(id_carrito).orElseThrow(() -> new RuntimeException("No se ha encontrado el carrito con la ID " + id_carrito));
            carritoRepository.delete(carrito);
            return "El carrito ha sido eliminado.";
        }catch(RuntimeException e){
            return e.getMessage();
        }
    }

    public CarritoDTO convertirCarritoDTO(Carrito carrito){
        CarritoDTO dto = new CarritoDTO();
        dto.setId_carrito(carrito.getId_carrito());
        dto.setCantidad(carrito.getCantidad());
        dto.setFecha_agregado(carrito.getFecha_agregado());
        return dto;
    }

}
