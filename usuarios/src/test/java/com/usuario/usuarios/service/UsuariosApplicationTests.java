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

import com.usuario.usuarios.dto.UsuarioDTO;
import com.usuario.usuarios.model.Usuario;
import com.usuario.usuarios.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuariosApplicationTests {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarPorIdExitoso() {
        Long idSimulado = 42L;
        Usuario usuarioSimulado = new Usuario();
        usuarioSimulado.setId_usuario(idSimulado);
        usuarioSimulado.setNombre("UsuarioTest");
        usuarioSimulado.setCorreo("correotest1@gmail.com");
        usuarioSimulado.setContrasena("test123");

        when(usuarioRepository.findById(idSimulado)).thenReturn(Optional.of(usuarioSimulado));
        UsuarioDTO resultado = usuarioService.buscaPorId(idSimulado);
        assertNotNull(resultado, "El DTO restante no deberia ser nulo");
        assertEquals(usuarioSimulado.getNombre(), resultado.getNombre(), "El nombre de usuario no coincide");
        assertEquals(usuarioSimulado.getCorreo(), resultado.getCorreo(), "El correo no coincide");
        verify(usuarioRepository, times(1)).findById(idSimulado);
    }

}
