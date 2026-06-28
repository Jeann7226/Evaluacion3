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

import com.usuario.usuarios.dto.CarritoDTO;
import com.usuario.usuarios.model.Carrito;
import com.usuario.usuarios.repository.CarritoRepository;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
public class CarritosApplicationTests {

    @Mock
    private CarritoRepository carritoRepository;

    @InjectMocks
    private CarritoService carritoService;

    private Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarPorIdExitoso() {
        Long idSimulado = 42L;
        Carrito carritoSimulado = new Carrito();
        carritoSimulado.setId_carrito(idSimulado);
        carritoSimulado.setCantidad(faker.number().numberBetween(1, 10));
        carritoSimulado.setFecha_agregado(java.time.LocalDate.now());

        when(carritoRepository.findById(idSimulado)).thenReturn(Optional.of(carritoSimulado));
        CarritoDTO resultado = carritoService.buscarCarrito(idSimulado);
        assertNotNull(resultado, "El DTO restante no deberia ser nulo");
        assertEquals(carritoSimulado.getCantidad(), resultado.getCantidad(), "La cantidad no coincide");
        assertEquals(carritoSimulado.getFecha_agregado(), resultado.getFecha_agregado(), "La fecha no coincide");
        verify(carritoRepository, times(1)).findById(idSimulado);
    }

}
