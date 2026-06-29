package com.autor.autores.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AutorDTO {

    @Schema(description = "ID del autor", example = "1")
    private Integer idAutor;
    private String nombre;
    private String apellido;

}
