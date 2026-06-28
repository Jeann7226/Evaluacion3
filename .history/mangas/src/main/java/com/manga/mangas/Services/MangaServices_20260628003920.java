package com.manga.mangas.Services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.manga.mangas.DTO.MangaDTO;
import com.manga.mangas.Model.Demografia;
import com.manga.mangas.Model.Manga;
import com.manga.mangas.Repository.MangaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MangaServices {
    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private DemografiaServices demografiaServices;

    @Autowired
    private InventarioServices inventarioServices;

    public List<MangaDTO> listarMangas(){
        return mangaRepository.findAll().stream().map(this::convertirMangaDTO).toList();
    }

    public Manga guardarManga(Manga manga){
        return mangaRepository.save(manga);
    }

    public MangaDTO buscarManga(Integer idManga){
        Manga manga = mangaRepository.findById(idManga).orElseThrow(() -> new RuntimeException("No se ha encontrado el manga con la ID " + idManga));
        return convertirMangaDTO(manga);
    }

    public Manga editarManga(Integer idManga, Manga manga){
        Manga manga1 = mangaRepository.findById(idManga).orElseThrow(() -> new RuntimeException("No se ha encontrado el manga."));
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

    public String eliminarManga(Integer idManga){
        try{
            Manga manga = mangaRepository.findById(idManga).orElseThrow(() -> new RuntimeException("No es posible eliminar el manga con ID '" + idManga + "' ya que no existe!."));
            mangaRepository.delete(manga);
            return "El manga '" + manga.getNombre() + "' ha sido eliminado!.";
        }catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public List<MangaDTO> buscarPorPrecioMaximo(Integer precioMax){
        return mangaRepository.buscarPorPrecioMaximo(precioMax).stream().map(this::convertirMangaDTO).toList();
    }

    public MangaDTO convertirMangaDTO(Manga manga) {
        MangaDTO dto = new MangaDTO();
        dto.setId_manga(manga.getIdManga());
        dto.setNombre(manga.getNombre());
        dto.setPrecio(manga.getPrecio());
        dto.setSinopsis(manga.getSinopsis());

        try {
            Demografia demografia = demografiaServices.buscarPorId(manga.getIdDemografia());
            dto.setInformacionExtra(demografia.getNombreDemografia()); 
        } catch (Exception e) {
            dto.setInformacionExtra("Información no disponible");
        }
        return dto;
    }
}
