package com.autor.autores.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.autor.autores.Controller.OrigenController;
import com.autor.autores.DTO.OrigenDTO;

public class OrigenModelAssembler implements RepresentationModelAssembler<OrigenDTO, EntityModel<OrigenDTO>> {
    @Override
    public EntityModel<OrigenDTO> toModel(OrigenDTO dto) {
        return EntityModel.of(dto,
            linkTo(methodOn(OrigenController.class).porId(dto.getIdOrigen())).withSelfRel(),

            linkTo(methodOn(OrigenController.class).todos()).withRel("origenes")
        );
    }

}
