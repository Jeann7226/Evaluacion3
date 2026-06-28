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

import com.autor.autores.DTO.AutorDTO;
import com.autor.autores.Model.Autor;
import com.autor.autores.Repository.AutorRepository;
import com.autor.autores.Service.AutorService;


@ExtendWith(MockitoExtension.class)
public class AutorApplicationTests {

    @Mock
    private AutorRepository autorRepository;
    
    @InjectMocks
    private AutorService autorService;

    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarPorIdExitoso() {
        Integer idSimulado = 42;
        Autor autorSimulado = new Autor();
        autorSimulado.setIdAutor(idSimulado);
        autorSimulado.setNombre("hirohiko");
        autorSimulado.setApellido("araki");

        when(autorRepository.findById(idSimulado)).thenReturn(Optional.of(autorSimulado));
        AutorDTO resultado = autorService.buscarPorId(idSimulado);
        assertNotNull(resultado, "El DTO restante no deberia ser nulo");
        assertEquals(autorSimulado.getNombre(), resultado.getNombre(), "El nombre no coincide");
        assertEquals(autorSimulado.getApellido(), resultado.getApellido(), "El apellido no coincide");
        verify(autorRepository, times(1)).findById(idSimulado);
    }

}
