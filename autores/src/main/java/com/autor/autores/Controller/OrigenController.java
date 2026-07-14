package com.autor.autores.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autor.autores.Assemblers.OrigenModelAssembler;
import com.autor.autores.DTO.OrigenDTO;
import com.autor.autores.Model.Origen;
import com.autor.autores.Service.OrigenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/origenes")
public class OrigenController {

    @Autowired
    private OrigenService origenService;

    @Autowired
    private OrigenModelAssembler origenModelAssembler;

    @GetMapping
    @Operation(summary = "Obtener todos los orígenes", description = "Obtiene una lista de todos los orígenes registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de orígenes obtenida exitosamente",
            content = @Content(mediaType = "application/json", 
            array = @ArraySchema(schema = @Schema(implementation = OrigenDTO.class))))
    public ResponseEntity<CollectionModel<EntityModel<OrigenDTO>>> todos() {
        List<OrigenDTO> lista = origenService.obtenerTodos();

        List<EntityModel<OrigenDTO>> origenesWithLinks = lista.stream()
            .map(origenModelAssembler::toModel)
            .toList();

            CollectionModel<EntityModel<OrigenDTO>> collectionModel = CollectionModel.of(origenesWithLinks,
                linkTo(methodOn(OrigenController.class).todos()).withSelfRel()
            );
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener origen por ID", description = "Obtiene un origen específico según su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode  = "200", description = "Origen encontrado con éxito",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = OrigenDTO.class))),
        @ApiResponse(responseCode = "404", description = "El ID del origen no existe en la base de datos",
            content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> porId(@PathVariable Integer id) {
        try {
            OrigenDTO dto = origenService.buscarPorId(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo origen", description = "Registra un nuevo origen en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Origen creado correctamente",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = OrigenDTO.class))),
        @ApiResponse(responseCode = "400", description = "Petición incorrecta o datos de validación inválidos",
            content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> registrarAutor(@Valid @RequestBody Origen origen) {
        try {
            OrigenDTO dto = origenService.guardarOrigen(origen);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error en el registro del origen", HttpStatus.BAD_REQUEST);
        }
    }
}