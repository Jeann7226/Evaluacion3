package com.autor.autores.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.autor.autores.Controller.OrigenController;
import com.autor.autores.Model.Origen;

public class OrigenModelAssembler implements RepresentationModelAssembler<Origen, EntityModel<Origen>> {
    @Override
    public EntityModel<Origen> toModel(Origen origen) {
        return EntityModel.of(origen,
            linkTo(methodOn(OrigenController.class).porId(origen.getIdOrigen())).withSelfRel()
        );
    }

}
