package com.manga.mangas.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manga.mangas.DTO.MangaDTO;
import com.manga.mangas.Model.Manga;
import com.manga.mangas.Repository.MangaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MangaServices {
    @Autowired
    private MangaRepository mangaRepository;

    public List<MangaDTO> listarMangas(){
        return mangaRepository.findAll().stream().map(this::convertirMangaDTO).toList();
    }

    public Manga guardarManga(Manga manga){
        return mangaRepository.save(manga);
    }
    public MangaDTO buscarManga(Integer id_manga){
        Manga manga = mangaRepository.findById(id_manga).orElseThrow(() -> new RuntimeException("No se ha encontrado el manga con la ID " + id_manga));
        return convertirMangaDTO(manga);
    }

    public Manga editarManga(Integer id_manga, Manga manga){
        Manga manga1 = mangaRepository.findById(id_manga).orElseThrow(() -> new RuntimeException("No se ha encontrado el manga."));
        if(manga.getNombre() != null){
            manga1.setNombre(manga.getNombre());
        }
        if(manga.getPrecio() != null){
            manga1.setPrecio(manga.getPrecio());
        }
        if(manga.getSinopsis() != null){
            manga1.setSinopsis(manga.getSinopsis());
        }
        return mangaRepository.save(manga1);
    }

    public String eliminarManga(Integer id_manga){
        try{
            Manga manga = mangaRepository.findById(id_manga).orElseThrow(() -> new RuntimeException("No es posible eliminar el manga con ID '" + id_manga + "' ya que no existe!."));
            mangaRepository.delete(manga);
            return "El manga '" + manga.getNombre() + "' ha sido eliminado!.";
        }catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    // Reemplazamos la búsqueda por género por tu query personalizada de precio
    public List<MangaDTO> buscarPorPrecioMaximo(Integer precioMax){
        return mangaRepository.buscarPorPrecioMaximo(precioMax).stream().map(this::convertirMangaDTO).toList();
    }

    public MangaDTO convertirMangaDTO(Manga manga){
        MangaDTO dto = new MangaDTO();
        dto.setId_manga(manga.getIdManga());
        dto.setNombre(manga.getNombre());
        dto.setPrecio(manga.getPrecio());
        dto.setSinopsis(manga.getSinopsis());
        return dto;
    }
}
