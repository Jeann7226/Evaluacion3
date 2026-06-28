package com.usuario.usuarios.service;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario.usuarios.dto.PagoDTO;
import com.usuario.usuarios.model.Pago;
import com.usuario.usuarios.repository.PagoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<PagoDTO> obtenerTodos(){
        return pagoRepository.findAll().stream().map(this::convertirPagoDTO).toList();
    }

    public PagoDTO buscarPorId(Long id_pago){
        Pago pago = pagoRepository.findById(id_pago)
            .orElseThrow(() -> new RuntimeException("el registro de pago no existe"));
            return convertirPagoDTO(pago);
    }


    public Pago registrarPago(Pago pago){
        pago.setFecha_transaccion(ZonedDateTime.now());
        return pagoRepository.save(pago);
    }
    
    public String eliminar(Long id_pago){
        try{
            Pago pago = pagoRepository.findById(id_pago)
            .orElseThrow(() -> new RuntimeException("el pago con el id '" + id_pago +"' no existe"));
            pagoRepository.delete(pago);
            return "el pago ha sido eliminado.";
        }catch (RuntimeException e){
            return e.getMessage();
        }
    }

    public List<PagoDTO> buscarHistorialPorUsuario(Long id_usuario) {
        return pagoRepository.buscarHistorialPorUsuario(id_usuario).stream().map(this::convertirPagoDTO).toList();
    }

    private PagoDTO convertirPagoDTO(Pago pago){
        PagoDTO dto = new PagoDTO();
        dto.setId_pago(pago.getId_pago());
        dto.setFecha_transaccion(pago.getFecha_transaccion());
        dto.setMetodo_pago(pago.getMetodo_pago());
        dto.setMonto(pago.getMonto());
        return dto;
    }

}
