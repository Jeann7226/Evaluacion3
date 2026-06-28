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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.manga.mangas.DTO.MangaDTO;
import com.manga.mangas.Model.Manga;
import com.manga.mangas.Services.MangaServices;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/mangas")
public class MangaController {
    @Autowired
    private MangaServices mangaService;

    @GetMapping
    public ResponseEntity<List<MangaDTO>> obtenerManga(){
        List<MangaDTO> mangas = mangaService.listarMangas();
        if(mangas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mangas, HttpStatus.OK);
    }

    @GetMapping("/{idManga}")
    public ResponseEntity<MangaDTO> buscarMangaId(@PathVariable Integer idManga){
        try{
            MangaDTO manga = mangaService.buscarManga(idManga);
            return new ResponseEntity<>(manga, HttpStatus.OK);
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/precio/{precioMax}")
    public ResponseEntity<List<MangaDTO>> filtrarPorPrecioMaximo(@PathVariable Integer precioMax){
        List<MangaDTO> mangas = mangaService.buscarPorPrecioMaximo(precioMax);
        if(mangas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mangas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Manga> guardarManga(@Valid @RequestBody Manga mangaNuevo){
        Manga manga = mangaService.guardarManga(mangaNuevo);
        if(manga != null){
            return new ResponseEntity<>(manga, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{idManga}")
    public ResponseEntity<Manga> editarManga(@PathVariable Integer idManga, @Valid @RequestBody Manga manga){
        Manga mangaEditado = mangaService.editarManga(idManga, manga);
        if(mangaEditado != null){
            return new ResponseEntity<>(mangaEditado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idManga}")
    public ResponseEntity<String> eliminarManga(@PathVariable Integer idManga){
        String resultado = mangaService.eliminarManga(idManga);
        if(resultado != null){
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

}
