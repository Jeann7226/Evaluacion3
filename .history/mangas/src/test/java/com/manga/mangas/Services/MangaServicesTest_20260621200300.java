package com.manga.mangas.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
            mangaFalso.setNombre("Dragon ball Z");

            when(mangaRepository.findById(idSimulado)).thenReturn(Optional.of(mangaFalso));
            String resultado = mangaService.eliminarManga(idSimulado);

            assertTrue(resultado.contains("ha sido eliminado"), "debe devolver el mensaje de exito");
            verify(mangaRepository, times(1)).delete(mangaFalso);
    }
    @Test
    void testEliminarManga_NoEncontrado() {
        
        Integer idInexistente = 99;

        
        when(mangaRepository.findById(idInexistente)).thenReturn(Optional.empty());

        
        String resultado = mangaService.eliminarManga(idInexistente);

        
        assertTrue(resultado.contains("ya que no existe"), "Debe devolver el mensaje de error del RuntimeException");
        verify(mangaRepository, times(0)).delete(any(Manga.class)); // Verificamos que NUNCA intentó borrar
    }
    @Test
    void testEditarManga_Exitoso() {
        
        Integer idSimulado = 10;
        Manga mangaAntiguo = new Manga();
        mangaAntiguo.setIdManga(idSimulado);
        mangaAntiguo.setNombre("Naruto Antiguo");
        
        Manga mangaNuevosDatos = new Manga();
        mangaNuevosDatos.setNombre("Naruto Shippuden");
        mangaNuevosDatos.setPrecio(15000);

       
        when(mangaRepository.findById(idSimulado)).thenReturn(Optional.of(mangaAntiguo));
        when(mangaRepository.save(any(Manga.class))).thenReturn(mangaAntiguo); // Save devuelve el mismo objeto actualizado

        
        Manga resultado = mangaService.editarManga(idSimulado, mangaNuevosDatos);

        
        assertNotNull(resultado);
        assertEquals("Naruto Shippuden", resultado.getNombre(), "El nombre debió actualizarse");
        verify(mangaRepository, times(1)).findById(idSimulado);
        verify(mangaRepository, times(1)).save(mangaAntiguo);
    }
    @Test
    void testBuscarPorId_Exitoso() {
      
        Integer idSimulado = 42;
        Manga mangaFalso = new Manga();
        mangaFalso.setIdManga(idSimulado);
        mangaFalso.setNombre(faker.book().title());
        mangaFalso.setPrecio(10000);
        mangaFalso.setSinopsis("Sinopsis de prueba");

        
        when(mangaRepository.findById(idSimulado)).thenReturn(Optional.of(mangaFalso));

        
        var resultado = mangaService.buscarManga(idSimulado);

        
        assertNotNull(resultado, "El DTO resultante no debería ser nulo");
        assertEquals(mangaFalso.getNombre(), resultado.getNombre(), "El nombre transformado al DTO debe coincidir con el de la DB");
        
        
        verify(mangaRepository, times(1)).findById(idSimulado);
    }
    
}

