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

import com.autor.autores.DTO.OrigenDTO;
import com.autor.autores.Model.Origen;
import com.autor.autores.Service.OrigenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/origenes")
public class OrigenController {

    @Autowired
    private OrigenService origenService;

    @GetMapping
    public ResponseEntity<List<OrigenDTO>> todos() {
        List<OrigenDTO> lista = origenService.obtenerTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> porId(@PathVariable Integer id) {
        try {
            OrigenDTO dto = origenService.buscarPorId(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> registrarAutor(@Valid @RequestBody Origen origen) {
        try {
            OrigenDTO dto = origenService.guardarOrigen(origen);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error en la busqueda del autor", HttpStatus.BAD_REQUEST);
        }
    }

}
