package com.manga.mangas.Services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.manga.mangas.Model.Inventario;
import com.manga.mangas.Repository.InventarioRepository;

@ExtendWith(MockitoExtension.class)
public class InventarioServicesTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioServices inventarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarInventario() {
        Inventario inv = new Inventario();
        inv.setIdInventario(1);
        inv.setStock(50);
        inv.setBodega("Bodega Central");

        when(inventarioRepository.save(any(Inventario.class))).thenReturn(inv);

        Inventario resultado = inventarioService.guardarInventario(inv);

        assertNotNull(resultado);
        assertEquals(50, resultado.getStock());
    }

    @Test
    void testBuscarInventario_Exitoso() {
        Inventario inv = new Inventario();
        inv.setIdInventario(3);
        inv.setStock(15);
        inv.setBodega("Bodega Sur");

        when(inventarioRepository.findById(3)).thenReturn(Optional.of(inv));

        var resultado = inventarioService.buscarInventario(3);

        assertNotNull(resultado);
        assertEquals("Bodega Sur", resultado.getBodega());
    }
}
