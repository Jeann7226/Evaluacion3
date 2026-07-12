package com.usuario.usuarios.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.usuario.usuarios.controller.CarritoController;
import com.usuario.usuarios.model.Carrito;

public class CarritoModelAssembler implements RepresentationModelAssembler<Carrito, EntityModel<Carrito>>{
    @Override
    public EntityModel<Carrito> toModel(Carrito carrito) {
        return EntityModel.of(carrito,
            linkTo(methodOn(CarritoController.class).buscarCarrito(carrito.getId_carrito())).withSelfRel()
        );
    }
}
