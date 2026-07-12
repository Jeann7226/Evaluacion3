package com.usuario.usuarios.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.usuario.usuarios.controller.PagoController;
import com.usuario.usuarios.model.Pago;

public class PagoModelAssembler implements RepresentationModelAssembler<Pago, EntityModel<Pago>>{
    @Override
    public EntityModel<Pago> toModel(Pago pago) {
        return EntityModel.of(pago,
            linkTo(methodOn(PagoController.class).buscarPago(pago.getId_pago())).withSelfRel()
        );
    }
}