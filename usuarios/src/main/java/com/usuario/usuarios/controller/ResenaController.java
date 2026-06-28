package com.usuario.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.usuarios.dto.ResenaDTO;
import com.usuario.usuarios.model.Resena;
import com.usuario.usuarios.service.ResenaService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaServices;
    
    @GetMapping
    public ResponseEntity<List<ResenaDTO>> listarResenas() {
        List<ResenaDTO> resenas = resenaServices.obtenerTodo();
        if (resenas.isEmpty()) {
            log.warn("HTTP NO_CONTENT: No se encontraron reseñas en la base de datos.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("HTTP OK: Se han listado las reseñas.");
        return new ResponseEntity<>(resenas, HttpStatus.OK);
    }

    @GetMapping("/{id_resena}")
    public ResponseEntity<ResenaDTO> buscarResena(@PathVariable Long id_resena) {
        try{
            ResenaDTO resena= resenaServices.buscarPorId(id_resena);
            log.info("HTTP OK: Se ha encontrado la reseña con la id " + id_resena);
            return new ResponseEntity<>(resena, HttpStatus.OK);
        }catch(RuntimeException e){
            log.error("HTTP NOT_FOUND: No se ha encontrado la reseña con la id " + id_resena);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Resena> guardarResena(@Valid @RequestBody Resena resenaNueva) {
        Resena resena = resenaServices.agregar(resenaNueva);
        if (resena != null) {
            log.info("HTTP CREATED: Se ha creado la reseña con la id " + resena.getId_resena());
            return new ResponseEntity<>(resena, HttpStatus.CREATED);
        }
        log.error("HTTP BAD_REQUEST: No se ha podido crear la reseña.");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id_resena}")
    public ResponseEntity<Resena> editarResena(@PathVariable Long id_resena, @RequestBody Resena resena) {
        Resena resenaEditada = resenaServices.actualizar(id_resena, resena);
        if (resenaEditada != null) {
            log.info("HTTP OK: Se ha actualizado la reseña con la id " + id_resena);
            return new ResponseEntity<>(resenaEditada, HttpStatus.OK);
        }
        log.error("HTTP NOT_FOUND: No se ha encontrado la reseña con la id " + id_resena);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id_resena}")
    public ResponseEntity<String> eliminarResena(@PathVariable Long id_resena) {
        String resultado = resenaServices.eliminar(id_resena);
        if (resultado.equals("La reseña ha sido eliminada.")) {
            log.info("HTTP OK: Se ha eliminado la reseña con la id " + id_resena);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
        log.error("HTTP NOT_FOUND: No se ha encontrado la reseña con la id " + id_resena);
        return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
    }

}
