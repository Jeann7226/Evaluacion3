package com.autor.autores.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autor.autores.DTO.OrigenDTO;
import com.autor.autores.Model.Origen;
import com.autor.autores.Repository.OrigenRepository;

@Service
public class OrigenService {

    @Autowired
    private OrigenRepository origenRepository;

    public List<OrigenDTO> obtenerTodos() {
        List<OrigenDTO> listaDTOs = new ArrayList<>();
        List<Origen> origenesReales = origenRepository.findAll();
        for (Origen o : origenesReales) {
            listaDTOs.add(convertirADTO(o));
        }
        return listaDTOs;
    }

    public OrigenDTO buscarPorId(Integer id) {
        Origen o = origenRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        return convertirADTO(o);
    }

    public OrigenDTO guardarOrigen(Origen nuevoOrigen) {
        Origen guardado = origenRepository.save(nuevoOrigen);
        return convertirADTO(guardado);
    }

    private OrigenDTO convertirADTO(Origen origen) {
        OrigenDTO dto = new OrigenDTO();
        dto.setIdOrigen(origen.getIdOrigen());
        dto.setPaisOrigen(origen.getPaisOrigen());
        return dto;
    }

}
