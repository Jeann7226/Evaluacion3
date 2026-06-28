package com.usuario.usuarios.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CarritoDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id_carrito;

    private Integer cantidad;
    private LocalDate fecha_agregado;

}
