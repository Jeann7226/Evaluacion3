package com.autor.autores.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.autor.autores.Controller.GeneroController;
import com.autor.autores.DTO.GeneroDTO;

@Component
public class GeneroModelAssembler implements RepresentationModelAssembler<GeneroDTO, EntityModel<GeneroDTO>> {
    @Override
    public EntityModel<GeneroDTO> toModel(GeneroDTO dto) {
        return EntityModel.of(dto,
            linkTo(methodOn(GeneroController.class).porId(dto.getIdGenero())).withSelfRel(),

            linkTo(methodOn(GeneroController.class).todos()).withRel("generos")
        );
    }

}
