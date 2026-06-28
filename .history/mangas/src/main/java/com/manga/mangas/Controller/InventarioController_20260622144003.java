package com.manga.mangas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;

import com.manga.mangas.DTO.InventarioDTO;
import com.manga.mangas.Model.Inventario;
import com.manga.mangas.Services.InventarioServices;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/inventarios")
public class InventarioController {

    @Autowired
    private InventarioServices inventarioService;

    
    @Operation(summary = "Listar inventarios", description = "Muestra todos los registros de stock y bodegas.")
    @GetMapping
    public ResponseEntity<List<InventarioDTO>> listarInventarios(){
        List<InventarioDTO> lista = inventarioService.listarInventarios();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    
    @Operation(summary = "Buscar inventario por ID", description = "Busca un registro de stock específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventario encontrado"),
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado", content = @Content)
    })
    @GetMapping("/{idInventario}")
    public ResponseEntity<?> buscarInventario(@PathVariable Integer idInventario){
        try{
            InventarioDTO dto = inventarioService.buscarInventario(idInventario);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch(RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
    @Operation(summary = "Registrar inventario", description = "Añade un nuevo registro de stock.")
    @PostMapping
    public ResponseEntity<?> guardarInventario(@Valid @RequestBody Inventario inventario){
        try {
            Inventario nuevo = inventarioService.guardarInventario(inventario);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar el inventario", HttpStatus.BAD_REQUEST);
        }
    }

    
    @Operation(summary = "Editar inventario", description = "Actualiza el stock y/o bodega de un registro.")
    @PutMapping("/{idInventario}")
    public ResponseEntity<?> editarInventario(@PathVariable Integer idInventario, @Valid @RequestBody Inventario inventario){
        try {
            Inventario editado = inventarioService.editarInventario(idInventario, inventario);
            return new ResponseEntity<>(editado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
    @Operation(summary = "Eliminar inventario", description = "Borra un registro de stock del sistema.")
    @DeleteMapping("/{idInventario}")
    public ResponseEntity<String> eliminarInventario(@PathVariable Integer idInventario){
        String resultado = inventarioService.eliminarInventario(idInventario);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}
