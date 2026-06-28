package com.manga.mangas.Services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.manga.mangas.Model.Demografia;
import com.manga.mangas.Repository.DemografiaRepository;

@ExtendWith(MockitoExtension.class)
class DemografiaServiceTest {

    @Mock
    private DemografiaRepository demografiaRepository;

    @InjectMocks
    private DemografiaServices demografiaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarDemografia() {
        Demografia demo = new Demografia();
        demo.setIdDemografia(1);
        demo.setNombreDemografia("Shonen");

        when(demografiaRepository.save(any(Demografia.class))).thenReturn(demo);

        Demografia resultado = demografiaService.guardarDemografia(demo);

        assertNotNull(resultado);
        assertEquals("Shonen", resultado.getNombreDemografia());
    }

    @Test
    void testBuscarDemografia_Exitoso() {
        Demografia demo = new Demografia();
        demo.setIdDemografia(2);
        demo.setNombreDemografia("Seinen");

        when(demografiaRepository.findById(2)).thenReturn(Optional.of(demo));

        var resultado = demografiaService.buscarDemografia(2);

        assertNotNull(resultado);
        assertEquals("Seinen", resultado.getNombreDemografia());
    }
}
