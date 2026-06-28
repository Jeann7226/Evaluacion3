package com.usuario.usuarios.service;

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

import com.usuario.usuarios.dto.ResenaDTO;
import com.usuario.usuarios.model.Resena;
import com.usuario.usuarios.repository.ResenaRepository;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
public class ResenasApplicationTests {

    @Mock
    private ResenaRepository resenaRepository;

    @InjectMocks
    private ResenaService resenaService;

    private Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarPorIdExitoso() {
        Long idSimulado = 42L;
        Resena resenaSimulada = new Resena();
        resenaSimulada.setId_resena(idSimulado);
        resenaSimulada.setCalificacion(faker.number().numberBetween(1, 10));
        resenaSimulada.setComentario("Comentario de prueba.");

        when(resenaRepository.findById(idSimulado)).thenReturn(Optional.of(resenaSimulada));
        ResenaDTO resultado = resenaService.buscarPorId(idSimulado);
        assertNotNull(resultado, "El DTO restante no deberia ser nulo");
        assertEquals(resenaSimulada.getCalificacion(), resultado.getCalificacion(), "La calficación no coincide.");
        assertEquals(resenaSimulada.getComentario(), resultado.getComentario(), "El comentario no coincide.");
        verify(resenaRepository, times(1)).findById(idSimulado);
    }

}
