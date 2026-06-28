package com.manga.mangas.Services;

import java.lang.annotation.Inherited;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@ExtendWith(MockitoExtension.class);
public class DemografiaServicesTest {
    @Mock
    private DemografiaServices demografiaService;

    @InjectMocks
    private DemografiaServices demografiaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);


}
