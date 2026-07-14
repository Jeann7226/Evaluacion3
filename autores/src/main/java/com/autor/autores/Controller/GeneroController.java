package com.autor.autores.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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
import com.autor.autores.Assemblers.GeneroModelAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @Autowired
    private GeneroModelAssembler generoModelAssembler;

    @GetMapping
    @Operation(summary = "Obtener todos los géneros", description = "Obtiene una lista de todos los géneros registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de géneros obtenida exitosamente",
            content = @Content(mediaType = "application/json", 
            array = @ArraySchema(schema = @Schema(implementation = GeneroDTO.class))))
    public ResponseEntity<CollectionModel<EntityModel<GeneroDTO>>> todos() {
        List<GeneroDTO> lista = generoService.obtenerTodos();

        List<EntityModel<GeneroDTO>> generosWithLinks = lista.stream()
            .map(generoModelAssembler::toModel)
            .toList();

            CollectionModel<EntityModel<GeneroDTO>> collectionModel = CollectionModel.of(generosWithLinks,
                linkTo(methodOn(GeneroController.class).todos()).withSelfRel()
            );
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener género por ID", description = "Obtiene un género específico según su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode  = "200", description = "Género encontrado con éxito",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = GeneroDTO.class))),
        @ApiResponse(responseCode = "404", description = "El ID del género no existe en la base de datos",
            content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> porId(@PathVariable Integer id) {
        try {
            GeneroDTO dto = generoService.buscarPorId(id);
            return new ResponseEntity<>(generoModelAssembler.toModel(dto), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo género", description = "Registra un nuevo género en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Género creado correctamente",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = GeneroDTO.class))),
        @ApiResponse(responseCode = "400", description = "Petición incorrecta o datos de validación inválidos",
            content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> registrarAutor(@Valid @RequestBody Genero genero) {
        try {
            GeneroDTO dto = generoService.guardarGenero(genero);
            return new ResponseEntity<>(generoModelAssembler.toModel(dto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error en el registro del género", HttpStatus.BAD_REQUEST);
        }
    }
}