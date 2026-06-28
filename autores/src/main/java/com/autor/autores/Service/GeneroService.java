package com.autor.autores.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autor.autores.DTO.GeneroDTO;
import com.autor.autores.Model.Genero;
import com.autor.autores.Repository.GeneroRepository;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public List<GeneroDTO> obtenerTodos() {
        List<GeneroDTO> listaDTOs = new ArrayList<>();
        List<Genero> generosReales = generoRepository.findAll();
        for (Genero g : generosReales) {
            listaDTOs.add(convertirADTO(g));
        }
        return listaDTOs;
    }

    public GeneroDTO buscarPorId(Integer id) {
        Genero g = generoRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        return convertirADTO(g);
    }

    public GeneroDTO guardarGenero(Genero nuevoGenero) {
        Genero guardado = generoRepository.save(nuevoGenero);
        return convertirADTO(guardado);
    }

    private GeneroDTO convertirADTO(Genero genero) {
        GeneroDTO dto = new GeneroDTO();
        dto.setIdGenero(genero.getIdGenero());
        dto.setGeneroTematico(genero.getGeneroTematico());
        return dto;
    }

}
