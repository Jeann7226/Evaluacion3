package com.manga.mangas.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@ExtendWith(MockitoExtension.class)
public class InventarioServicesTest {

    @Mock
    private InventarioServices inventarioService;

    @InjectMocks
    private InventarioServices inventarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

}
