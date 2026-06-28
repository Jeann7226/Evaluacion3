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

import com.manga.mangas.DTO.MangaDTO;
import com.manga.mangas.Model.Manga;
import com.manga.mangas.Services.MangaServices;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/mangas")
public class MangaController {

    @Autowired
    private MangaServices mangaService;

    
    @Operation(summary = "Listar todos los mangas", description = "Devuelve una lista completa de todos los mangas disponibles en la aduana de la base de datos.")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<MangaDTO>> obtenerManga(){
        List<MangaDTO> mangas = mangaService.listarMangas();
        if(mangas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mangas, HttpStatus.OK);
    }

   
    @Operation(summary = "Buscar manga por ID", description = "Busca un manga específico mediante su ID y trae la información externa.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Manga encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Manga no encontrado", content = @Content)
    })
    @GetMapping("/{idManga}")
    public ResponseEntity<?> buscarMangaId(@PathVariable Integer idManga){
        try{
            MangaDTO manga = mangaService.buscarManga(idManga);
            return new ResponseEntity<>(manga, HttpStatus.OK);
        } catch(RuntimeException e){
            // Aquí devolvemos el texto del error si falla, igual que el profe
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
    @Operation(summary = "Filtrar por precio máximo", description = "Devuelve los mangas que cuesten igual o menos que el valor indicado.")
    @ApiResponse(responseCode = "200", description = "Filtro aplicado correctamente")
    @GetMapping("/precio/{precioMax}")
    public ResponseEntity<List<MangaDTO>> filtrarPorPrecioMaximo(@PathVariable Integer precioMax){
        List<MangaDTO> mangas = mangaService.buscarPorPrecioMaximo(precioMax);
        if(mangas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mangas, HttpStatus.OK);
    }

    
    @Operation(summary = "Registrar un nuevo manga", description = "Crea y guarda un nuevo manga cruzando los datos hacia el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Manga creado con éxito"),
        @ApiResponse(responseCode = "400", description = "Error en los datos ingresados", content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> guardarManga(@Valid @RequestBody Manga mangaNuevo){
        try {
            Manga manga = mangaService.guardarManga(mangaNuevo);
            return new ResponseEntity<>(manga, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar el manga", HttpStatus.BAD_REQUEST);
        }
    }

    
    @Operation(summary = "Editar un manga existente", description = "Actualiza los datos de un manga específico según su ID.")
    @PutMapping("/{idManga}")
    public ResponseEntity<?> editarManga(@PathVariable Integer idManga, @Valid @RequestBody Manga manga){
        try {
            Manga mangaEditado = mangaService.editarManga(idManga, manga);
            return new ResponseEntity<>(mangaEditado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
    @Operation(summary = "Eliminar un manga", description = "Borra permanentemente un manga de los registros.")
    @DeleteMapping("/{idManga}")
    public ResponseEntity<?> eliminarManga(@PathVariable Integer idManga){
        try {
            String resultado = mangaService.eliminarManga(idManga);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}