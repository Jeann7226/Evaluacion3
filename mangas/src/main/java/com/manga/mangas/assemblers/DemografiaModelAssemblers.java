package com.manga.mangas.assemblers;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import com.manga.mangas.Controller.DemografiaController;
import com.manga.mangas.Model.Demografia;

@Component
public class DemografiaModelAssemblers implements org.springframework.hateoas.server.RepresentationModelAssembler<Demografia, EntityModel<Demografia>> {
    @Override
    public EntityModel<Demografia> toModel(Demografia demografia) {
        return EntityModel.of(demografia,
            linkTo(methodOn(DemografiaController.class).buscarDemografia(demografia.getIdDemografia())).withSelfRel()
        );
    }

}
