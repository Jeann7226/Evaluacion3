package com.manga.mangas.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.annotation.Target;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.manga.mangas.Model.Manga;
import com.manga.mangas.Repository.MangaRepository;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
public class MangaServicesTest {
    @Mock
    private MangaRepository mangaRepository;

    @InjectMocks
    private MangaServices mangaService;

    private Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarManga() {
        Manga mangaFalso = new Manga();
        mangaFalso.setIdManga(1);
        mangaFalso.setNombre(faker.book().title());
        mangaFalso.setPrecio(faker.number().numberBetween(5000, 20000));
        mangaFalso.setSinopsis("AGUANTE EL POKEMON");

        when(mangaRepository.save(any(Manga.class))).thenReturn(mangaFalso);

        Manga resultado = mangaService.guardarManga(mangaFalso);

        assertNotNull(resultado, "El manga guardado no debe ser nulo");
        assertEquals(mangaFalso.getNombre(), resultado.getNombre(),"los nombres deben coincidir");
        verify(mangaRepository, times(1)).save(mangaFalso);
    }
   
    @Test
    void testEliminarManga_Exitoso(){
        Integer idSimulado = 5;
            Manga mangaFalso = new Manga();
            mangaFalso.setIdManga(idSimulado);
            mangaFalso.setDemografia("Dragon ball Z");
        }
}

