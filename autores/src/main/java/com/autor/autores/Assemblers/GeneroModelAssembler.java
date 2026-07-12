package com.autor.autores.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.autor.autores.Controller.GeneroController;
import com.autor.autores.Model.Genero;

public class GeneroModelAssembler implements RepresentationModelAssembler<Genero, EntityModel<Genero>> {
    @Override
    public EntityModel<Genero> toModel(Genero genero) {
        return EntityModel.of(genero,
            linkTo(methodOn(GeneroController.class).porId(genero.getIdGenero())).withSelfRel()
        );
    }

}
