package com.usuario.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.usuarios.dto.PagoDTO;
import com.usuario.usuarios.model.Pago;
import com.usuario.usuarios.service.PagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;
    
    @Operation(summary = "Listar Pagos", description = "Lista todos los pagos disponibles.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pagos encontrados"),
        @ApiResponse(responseCode = "404", description = "Pagos no encontrados", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<PagoDTO>> listarPagos() {
        List<PagoDTO> pagos = pagoService.obtenerTodos();
        if (pagos.isEmpty()) {
            log.warn("HTTP NO_CONTENT: No se encontraron pagos en la base de datos.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("HTTP OK: Se han listado los pagos.");
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }
    
    @Operation(summary = "Buscar Pago por ID", description = "Busca un pago específico por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago encontrado"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado", content = @Content)
    })
    @GetMapping("/{id_pago}")
    public ResponseEntity<PagoDTO> buscarPago(@PathVariable Long id_pago) {
        try{
            PagoDTO pago = pagoService.buscarPorId(id_pago);
            log.info("HTTP OK: Se ha encontrado el pago con la id " + id_pago);
            return new ResponseEntity<>(pago, HttpStatus.OK);
        }catch(RuntimeException e){
            log.error("HTTP NOT_FOUND: No se ha encontrado el pago con la id " + id_pago);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Registrar Pago", description = "Crea un nuevo pago en la base de datos.")
    @PostMapping
    public ResponseEntity<Pago> procesarPago(@Valid @RequestBody Pago pagoNuevo) {
        Pago pago = pagoService.registrarPago(pagoNuevo);
        if (pago != null) {
            log.info("HTTP CREATED: Se ha creado el pago con la id " + pago.getId_pago());
            return new ResponseEntity<>(pago, HttpStatus.CREATED);
        }
        log.error("HTTP BAD_REQUEST: No se ha podido crear el pago.");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Eliminar Pago", description = "Elimina un pago existente por su ID.")
    @DeleteMapping("/{id_pago}")
    public ResponseEntity<String> eliminarPago(@PathVariable Long id_pago) {
        String resultado = pagoService.eliminar(id_pago);
        if (resultado.equals("El pago ha sido eliminado.")) {
            log.info("HTTP OK: Se ha eliminado el pago con la id " + id_pago);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
        log.error("HTTP NOT_FOUND: No se ha encontrado el pago con la id " + id_pago);
        return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Buscar Historial de Pagos por Usuario", description = "Busca el historial de pagos para un usuario específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pagos encontrados"),
        @ApiResponse(responseCode = "404", description = "Pagos no encontrados", content = @Content)
    })
    @GetMapping("/usuario/{id_usuario}")
    public ResponseEntity<List<PagoDTO>> buscarHistorialPorUsuario(@PathVariable Long id_usuario) {
        List<PagoDTO> pagos = pagoService.buscarHistorialPorUsuario(id_usuario);
        if (pagos.isEmpty()) {
            log.warn("HTTP NO_CONTENT: No se encontraron pagos para el usuario con la id " + id_usuario);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("HTTP OK: Se han encontrado pagos para el usuario con la id " + id_usuario);
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

}
