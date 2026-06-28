package com.manga.mangas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manga.mangas.DTO.DemografiaDTO;
import com.manga.mangas.Model.Demografia;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/demografias")
public class DemografiaController {
    @Autowired
    private DemografiaService demografiaService;

    // 🟢 LISTAR TODOS (Devuelve lista de DTOs)
    @Operation(summary = "Listar demografías", description = "Muestra todas las categorías demográficas disponibles.")
    @GetMapping
    public ResponseEntity<List<DemografiaDTO>> listarDemografias(){
        List<DemografiaDTO> lista = demografiaService.listarDemografias();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // 🟢 BUSCAR POR ID (Devuelve DTO)
    @Operation(summary = "Buscar demografía por ID", description = "Busca una categoría específica mediante su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Demografía encontrada"),
        @ApiResponse(responseCode = "404", description = "Demografía no encontrada", content = @Content)
    })
    @GetMapping("/{id_demografia}")
    public ResponseEntity<?> buscarDemografia(@PathVariable Integer id_demografia){
        try{
            DemografiaDTO dto = demografiaService.buscarDemografia(id_demografia);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch(RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // 🟠 GUARDAR (Recibe y devuelve la entidad completa)
    @Operation(summary = "Registrar demografía", description = "Añade una nueva categoría a la base de datos.")
    @PostMapping
    public ResponseEntity<?> guardarDemografia(@Valid @RequestBody Demografia demografia){
        try {
            Demografia nueva = demografiaService.guardarDemografia(demografia);
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar la demografía", HttpStatus.BAD_REQUEST);
        }
    }

    // 🔵 EDITAR
    @Operation(summary = "Editar demografía", description = "Actualiza el nombre de una demografía existente.")
    @PutMapping("/{id_demografia}")
    public ResponseEntity<?> editarDemografia(@PathVariable Integer id_demografia, @Valid @RequestBody Demografia demografia){
        try {
            Demografia editada = demografiaService.editarDemografia(id_demografia, demografia);
            return new ResponseEntity<>(editada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // 🔴 ELIMINAR (Tu service ya maneja el error internamente y devuelve un String)
    @Operation(summary = "Eliminar demografía", description = "Borra una categoría de los registros.")
    @DeleteMapping("/{id_demografia}")
    public ResponseEntity<String> eliminarDemografia(@PathVariable Integer id_demografia){
        String resultado = demografiaService.eliminarDemografia(id_demografia);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}
