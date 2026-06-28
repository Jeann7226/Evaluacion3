package com.usuario.usuarios.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ssXXX", timezone = "America/Santiago")
public class PagoDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id_pago;

    private Integer monto;
    private String metodo_pago;
    private ZonedDateTime fecha_transaccion;

}
