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

import com.autor.autores.DTO.GeneroDTO;
import com.autor.autores.Model.Genero;
import com.autor.autores.Repository.GeneroRepository;
import com.autor.autores.Service.GeneroService;

@ExtendWith(MockitoExtension.class)
public class GenerosApplicationTests {

    @Mock
    private GeneroRepository generoRepository;

    @InjectMocks
    private GeneroService generoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarPorIdExitoso() {
        Integer idSimulado = 42;
        Genero generoSimulado = new Genero();
        generoSimulado.setIdGenero(idSimulado);
        generoSimulado.setGeneroTematico("Ficcion");

        when(generoRepository.findById(idSimulado)).thenReturn(Optional.of(generoSimulado));
        GeneroDTO resultado = generoService.buscarPorId(idSimulado);
        assertNotNull(resultado, "El DTO restante no deberia ser nulo");
        assertEquals(generoSimulado.getGeneroTematico(), resultado.getGeneroTematico(), "El genero tematico no coincide");
        verify(generoRepository, times(1)).findById(idSimulado);

    }

}
