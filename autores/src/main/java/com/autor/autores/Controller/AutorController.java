package com.autor.autores.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autor.autores.DTO.AutorDTO;
import com.autor.autores.Model.Autor;
import com.autor.autores.Service.AutorService;
import com.autor.autores.Assemblers.AutorModelAssembler;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/autores")
@Tag(name = "Autor Controller", description = "Controlador para gestionar operaciones relacionadas con autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @Autowired
    private AutorModelAssembler autorModelAssembler;

    @GetMapping
    @Operation(summary = "Obtener todos los autores", description = "Obtiene una lista de todos los autores registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de autores obtenida exitosamente",
            content = @Content(mediaType = "application/json", 
            array = @ArraySchema(schema = @Schema(implementation = AutorDTO.class))))
    public ResponseEntity<CollectionModel<EntityModel<AutorDTO>>> todos() {
        List<AutorDTO> lista = autorService.obtenerTodos();

        List<EntityModel<AutorDTO>> autoresWithLinks = lista.stream()
            .map(autorModelAssembler::toModel)
            .toList();

            CollectionModel<EntityModel<AutorDTO>> collectionModel = CollectionModel.of(autoresWithLinks,
                linkTo(methodOn(AutorController.class).todos()).withSelfRel()
            );
        return new ResponseEntity<>(CollectionModel.of(collectionModel), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener autor por ID", description = "Obtiene un autor específico según su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode  = "200", description = "Autor encontrado con éxito",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = AutorDTO.class))),
        @ApiResponse(responseCode = "404", description = "El ID del autor no existe en la base de datos",
            content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> porId(@PathVariable Integer id) {
        try {
            AutorDTO dto = autorService.buscarAutor(id);
            return new ResponseEntity<>(autorModelAssembler.toModel(dto), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo autor", description = "Registra un nuevo autor en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Autor creado correctamente",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = AutorDTO.class))),
        @ApiResponse(responseCode = "400", description = "Petición incorrecta o datos de validación inválidos",
            content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> registrarAutor(@Valid @RequestBody Autor autor) {
        try {
            AutorDTO dto = autorService.guardarAutor(autor);
            return new ResponseEntity<>(autorModelAssembler.toModel(dto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error en la búsqueda del autor", HttpStatus.BAD_REQUEST);
        }
    }
}