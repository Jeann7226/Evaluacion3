package com.usuario.usuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario.usuarios.dto.ResenaDTO;
import com.usuario.usuarios.model.Resena;
import com.usuario.usuarios.repository.ResenaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    public List<ResenaDTO> obtenerTodo(){
        return resenaRepository.findAll().stream().map(this::convertirResenaDTO).toList();
    }
    
    public ResenaDTO buscarPorId(Long id_resena){
        Resena resena = resenaRepository.findById(id_resena)
            .orElseThrow(() -> new RuntimeException("la reseña no existe"));
            return convertirResenaDTO(resena);
    }

    public Resena agregar(Resena resena){
        return resenaRepository.save(resena);
    }

    public Resena actualizar(Long id_resena, Resena resenaActualizada){
        Resena reseñaExistente = resenaRepository.findById(id_resena)
        .orElseThrow(() -> new RuntimeException("la reseña no existe en los registros"));
        
        if(resenaActualizada.getComentario() != null){
            reseñaExistente.setComentario(resenaActualizada.getComentario());
        }
        if(resenaActualizada.getCalificacion() != null){
            reseñaExistente.setCalificacion(resenaActualizada.getCalificacion());
        }
        
        return resenaRepository.save(reseñaExistente);
    }

    public String eliminar(Long id_resena){
        try{
            Resena reseña = resenaRepository.findById(id_resena)
            .orElseThrow(() -> new RuntimeException("la reseña con el id '" + id_resena +"' no existe"));
            resenaRepository.delete(reseña);
            return "la reseña ha sido eliminada.";
        }catch (RuntimeException e){
            return e.getMessage();
        }
    }

    private ResenaDTO convertirResenaDTO(Resena resena){
        ResenaDTO dto = new ResenaDTO();
        dto.setId_resena(resena.getId_resena());
        dto.setCalificacion(resena.getCalificacion());
        dto.setComentario(resena.getComentario());
        return dto;
    }
    
}
