package com.manga.mangas.assemblers;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import com.manga.mangas.Controller.InventarioController;
import com.manga.mangas.Model.Inventario;

@Component
public class InventarioModelAssemblers implements org.springframework.hateoas.server.RepresentationModelAssembler<Inventario, EntityModel<Inventario>> {
    @Override
    public EntityModel<Inventario> toModel(Inventario inventario) {
        return EntityModel.of(inventario,
            linkTo(methodOn(InventarioController.class).buscarInventario(inventario.getIdInventario())).withSelfRel()
        );
    }

}
