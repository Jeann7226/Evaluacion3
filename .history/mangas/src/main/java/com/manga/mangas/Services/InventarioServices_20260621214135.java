package com.manga.mangas.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manga.mangas.DTO.InventarioDTO;
import com.manga.mangas.Model.Inventario;
import com.manga.mangas.Repository.InventarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional 

public class InventarioServices {
    
    @Autowired
    private InventarioRepository inventarioRepository;

    public List<InventarioDTO> listarInventarios(){
        return inventarioRepository.findAll().stream().map(this::convertirInventarioDTO).toList();
    }

    public Inventario guardarInventario(Inventario inventario){
        return inventarioRepository.save(inventario);
    }

    public InventarioDTO buscarInventario(Integer idInventario){
        Inventario inventario = inventarioRepository.findById(idInventario).orElseThrow(() -> new RuntimeException("No se ha encontrado el inventario con la ID " + idInventario));
        return convertirInventarioDTO(inventario);
    }

    public Inventario editarInventario(Integer idInventario, Inventario inventario1){
        Inventario inventario = inventarioRepository.findById(idInventario).orElseThrow(() -> new RuntimeException("No se ha encontrado el inventario con la ID " + idInventario));
        if(inventario1.getStock() != null){
            inventario.setStock(inventario1.getStock());
        }
        if(inventario1.getBodega() != null){
            inventario.setBodega(inventario1.getBodega());
        }
        
        return inventarioRepository.save(inventario);
    }

    public String eliminarInventario(Integer idInventario){
        try{
            Inventario inventario = inventarioRepository.findById(idInventario).orElseThrow(() -> new RuntimeException("No se ha encontrado el inventario con la ID " + idInventario));
            inventarioRepository.delete(inventario);
            return "El inventario ha sido eliminado.";
        }catch(RuntimeException e){
            return e.getMessage();
        }
    }

    public InventarioDTO convertirInventarioDTO(Inventario inventario){
        InventarioDTO dto = new InventarioDTO();
        dto.setIdInventario(inventario.getIdInventario());
        dto.setStock(inventario.getStock());
        dto.setBodega(inventario.getBodega());
        return dto;
    }
}

