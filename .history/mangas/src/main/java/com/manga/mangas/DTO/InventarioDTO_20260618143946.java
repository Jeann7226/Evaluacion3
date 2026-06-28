package com.manga.mangas.DTO;

import lombok.Data;

@Data
public class InventarioDTO {
    private Integer id_inventario;
    private Integer stock;
    private String bodega; 
}
