package com.usuario.usuarios.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.usuario.usuarios.controller.ResenaController;
import com.usuario.usuarios.model.Resena;

public class ResenaModelAssembler implements RepresentationModelAssembler<Resena, EntityModel<Resena>>{
    @Override
    public EntityModel<Resena> toModel(Resena resena) {
        return EntityModel.of(resena,
            linkTo(methodOn(ResenaController.class).buscarResena(resena.getId_resena())).withSelfRel()
        );
    }
}
