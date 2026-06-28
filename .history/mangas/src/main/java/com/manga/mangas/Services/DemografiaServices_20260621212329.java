package com.manga.mangas.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manga.mangas.DTO.DemografiaDTO;
import com.manga.mangas.Model.Demografia;
import com.manga.mangas.Repository.DemografiaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DemografiaServices {
    
    @Autowired
    private DemografiaRepository demografiaRepository;

    public List<DemografiaDTO> listarDemografias(){
        return demografiaRepository.findAll().stream().map(this::convertirDemografiaDTO).toList();
    }

    public Demografia guardarDemografia(Demografia demografia){
        return demografiaRepository.save(demografia);
    }

    public DemografiaDTO buscarDemografia(Integer id_demografia){
        Demografia demografia = demografiaRepository.findById(id_demografia).orElseThrow(() -> new RuntimeException("No se ha encontrado la demografia con la ID " + id_demografia));
        return convertirDemografiaDTO(demografia);
    }

    public Demografia editarDemografia(Integer id_demografia, Demografia demografia1){
        Demografia demografia = demografiaRepository.findById(id_demografia).orElseThrow(() -> new RuntimeException("No se ha encontrado la demografia con la ID " + id_demografia));
        if(demografia.getNombreDemografia() != null){
            demografia.setNombreDemografia(demografia1.getNombreDemografia());
        }
        return demografiaRepository.save(demografia);
    }

    public String eliminarDemografia(Integer id_demografia){
        try{
            Demografia demografia = demografiaRepository.findById(id_demografia).orElseThrow(() -> new RuntimeException("No se ha encontrado la demografia con la ID" + id_demografia));
            demografiaRepository.delete(demografia);
            return "La demografia ha sido eliminada.";
        }catch(RuntimeException e){
            return e.getMessage();
        }
    }

    public DemografiaDTO convertirDemografiaDTO(Demografia demografia){
        DemografiaDTO dto = new DemografiaDTO();
        dto.setId_demografia(demografia.getIdDemografia());
        dto.setNombreDemografia(demografia.getNombreDemografia());
        return dto;
    }
}

}
