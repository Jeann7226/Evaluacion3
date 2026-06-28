package com.autor.autores.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.autor.autores.DTO.OrigenDTO;
import com.autor.autores.Model.Origen;
import com.autor.autores.Repository.OrigenRepository;
import com.autor.autores.Service.OrigenService;

@ExtendWith(MockitoExtension.class)
public class OrigenesApplicationTests {

    @Mock
    private OrigenRepository origenRepository;

    @InjectMocks
    private OrigenService origenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarPorIdExitoso() {
        Integer idSimulado = 42;
        Origen origenSimulado = new Origen();
        origenSimulado.setIdOrigen(idSimulado);
        origenSimulado.setPaisOrigen("Japon");

        when(origenRepository.findById(idSimulado)).thenReturn(Optional.of(origenSimulado));
        OrigenDTO resultado = origenService.buscarPorId(idSimulado);
        assertNotNull(resultado, "El DTO restante no debe ser nulo");
        assertEquals(origenSimulado.getPaisOrigen(), resultado.getPaisOrigen(), "El pais de origen no coincide");
        verify(origenRepository, times(1)).findById(idSimulado);

    }

}
