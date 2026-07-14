package com.autor.autores.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.autor.autores.Controller.AutorController;
import com.autor.autores.DTO.AutorDTO;

@Component
public class AutorModelAssembler implements RepresentationModelAssembler<AutorDTO, EntityModel<AutorDTO>> {
    @Override
    public EntityModel<AutorDTO> toModel(AutorDTO dto) {
        return EntityModel.of(dto,
            linkTo(methodOn(AutorController.class).porId(dto.getIdAutor())).withSelfRel(),

            linkTo(methodOn(AutorController.class).todos()).withRel("autores")
        );
    }
}
