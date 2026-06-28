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

import com.usuario.usuarios.dto.PagoDTO;
import com.usuario.usuarios.model.Pago;
import com.usuario.usuarios.repository.PagoRepository;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
public class PagosApplicationTests {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService PagoService;

    private Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarPorIdExitoso() {
        Long idSimulado = 42L;
        Pago pagoSimulado = new Pago();
        pagoSimulado.setId_pago(idSimulado);
        pagoSimulado.setMonto(faker.number().numberBetween(3000, 50000));
        pagoSimulado.setMetodo_pago("BancoEstado - Débito");
        pagoSimulado.setFecha_transaccion(java.time.ZonedDateTime.now());

        when(pagoRepository.findById(idSimulado)).thenReturn(Optional.of(pagoSimulado));
        PagoDTO resultado = PagoService.buscarPorId(idSimulado);
        assertNotNull(resultado, "El DTO restante no deberia ser nulo.");
        assertEquals(pagoSimulado.getMonto(), resultado.getMonto(), "El monto no coincide.");
        assertEquals(pagoSimulado.getMetodo_pago(), resultado.getMetodo_pago(), "El método de pago no coincide.");
        assertEquals(pagoSimulado.getFecha_transaccion(), resultado.getFecha_transaccion(), "La fecha de la transacción no coincide.");
        verify(pagoRepository, times(1)).findById(idSimulado);
    }

}
