package com.autor.autores.DTO;

import lombok.Data;

@Data
public class UsuarioExternoDTO {

    private Integer id_usuario;
    private String nombre;
    private String correo;
    private String contrasena;

}
