package com.usuario.usuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UsuarioDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id_usuario;

    private String nombre;
    private String correo;

}