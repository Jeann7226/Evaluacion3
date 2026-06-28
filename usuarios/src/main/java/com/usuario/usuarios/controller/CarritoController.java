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

import com.usuario.usuarios.dto.CarritoDTO;
import com.usuario.usuarios.model.Carrito;
import com.usuario.usuarios.service.CarritoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Operation(summary = "Buscar inventario por ID", description = "Busca un registro de stock específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventario encontrado"),
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<CarritoDTO>> listarCarritos(){
        List<CarritoDTO> carritos = carritoService.listarCarritos();
        if(carritos.isEmpty()){
            log.warn("HTTP NO_CONTENT: No se encontraron carritos en la base de datos.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("HTTP OK: Se han listado los carritos.");
        return new ResponseEntity<>(carritos, HttpStatus.OK);
    }

    @GetMapping("/{id_carrito}")
    public ResponseEntity<CarritoDTO> buscarCarrito(@PathVariable Long id_carrito){
        try{
            CarritoDTO carrito = carritoService.buscarCarrito(id_carrito);
            log.info("HTTP OK: Se ha encontrado el carrito con la id " + id_carrito);
            return new ResponseEntity<>(carrito, HttpStatus.OK);
        } catch(RuntimeException e){
            log.error("HTTP NOT_FOUND: No se ha encontrado el carrito con la id " + id_carrito);
            return ResponseEntity.notFound().build();
        }     
    }
        

    @PostMapping
    public ResponseEntity<Carrito> guardarCarrito(@Valid @RequestBody Carrito carrito1){
        Carrito carrito = carritoService.guardarCarrito(carrito1);
        if(carrito != null){
            log.info("HTTP CREATED: Se ha creado el carrito con la id " + carrito.getId_carrito());
            return new ResponseEntity<>(carrito, HttpStatus.CREATED);
        }
        else{
            log.error("HTTP BAD_REQUEST: No se ha podido crear el carrito.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id_carrito}")
    public ResponseEntity<Carrito> editarCarrito(@PathVariable Long id_carrito, @RequestBody Carrito carrito){
        Carrito carritoEditado = carritoService.editarCarrito(id_carrito, carrito);
        if(carritoEditado != null){
            log.info("HTTP OK: Se ha editado el carrito con la id " + id_carrito);
            return new ResponseEntity<>(carritoEditado, HttpStatus.OK);
        }
        else{
            log.error("HTTP NOT_FOUND: No se ha encontrado el carrito con la id " + id_carrito);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_carrito}")
    public ResponseEntity<String> eliminarCarrito(@PathVariable Long id_carrito){
        String resultado = carritoService.eliminarCarrito(id_carrito);
        if(resultado.equals("El carrito ha sido eliminado.")){
            log.info("HTTP OK: Se ha eliminado el carrito con la id " + id_carrito);
            return new ResponseEntity<>(resultado, HttpStatus.OK); 
        }
        else{
            log.error("HTTP NOT_FOUND: No se ha encontrado el carrito con la id " + id_carrito);
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

}
