package com.usuario.usuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResenaDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id_resena;

    private Integer calificacion;
    private String comentario;

}
