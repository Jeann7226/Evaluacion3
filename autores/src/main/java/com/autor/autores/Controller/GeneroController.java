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

import com.autor.autores.DTO.GeneroDTO;
import com.autor.autores.Model.Genero;
import com.autor.autores.Service.GeneroService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    @Operation(summary = "Obtener todos los generos", description = "Obtiene una lista de todos los generos registrados en el sistema.")
    public ResponseEntity<List<GeneroDTO>> todos() {
        List<GeneroDTO> lista = generoService.obtenerTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener genero por ID", description = "Obtiene un genero específico según su ID.")
    public ResponseEntity<?> porId(@PathVariable Integer id) {
        try {
            GeneroDTO dto = generoService.buscarPorId(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo genero", description = "Registra un nuevo genero en el sistema.")
    public ResponseEntity<?> registrarAutor(@Valid @RequestBody Genero genero) {
        try {
            GeneroDTO dto = generoService.guardarGenero(genero);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error en la busqueda del autor", HttpStatus.BAD_REQUEST);
        }
    }


}
