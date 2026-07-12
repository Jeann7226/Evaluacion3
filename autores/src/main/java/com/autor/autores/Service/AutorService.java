package com.autor.autores.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autor.autores.DTO.AutorDTO;
import com.autor.autores.Model.Autor;
import com.autor.autores.Repository.AutorRepository;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<AutorDTO> obtenerTodos() {
        List<AutorDTO> listaDTOs = new ArrayList<>();
        List<Autor> autoresReales = autorRepository.findAll();
        for (Autor a : autoresReales) {
            listaDTOs.add(convertirADTO(a));
        }
        return listaDTOs;
    }

    public AutorDTO buscarAutor(Integer id) {
        Autor a = autorRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        return convertirADTO(a);
    }

    public AutorDTO guardarAutor(Autor nuevoAutor) {
        Autor guardado = autorRepository.save(nuevoAutor);
        return convertirADTO(guardado);
    }

    private AutorDTO convertirADTO(Autor autor) {
        AutorDTO dto = new AutorDTO();
        dto.setIdAutor(autor.getIdAutor());
        dto.setNombre(autor.getNombre());
        dto.setApellido(autor.getApellido());
        return dto;
    }

}
