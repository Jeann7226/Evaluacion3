package com.autor.autores.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autor.autores.DTO.AutorDTO;
import com.autor.autores.Model.Autor;
import com.autor.autores.Service.AutorService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;


@RestController
@RequestMapping("api/v1/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    @Operation(summary = "Obtener todos los autores", description = "Obtiene una lista de todos los autores registrados en el sistema.")
    public ResponseEntity<List<AutorDTO>> todos() {
        List<AutorDTO> lista = autorService.obtenerTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener autor por ID", description = "Obtiene un autor específico según su ID.")
    public ResponseEntity<?> porId(@PathVariable Integer id) {
        try {
            AutorDTO dto = autorService.buscarPorId(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo autor", description = "Registra un nuevo autor en el sistema.")
    public ResponseEntity<?> registrarAutor(@Valid @RequestBody Autor autor) {
        try {
            AutorDTO dto = autorService.guardarAutor(autor);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error en la busqueda del autor", HttpStatus.BAD_REQUEST);
        }
    }
    
    

}
