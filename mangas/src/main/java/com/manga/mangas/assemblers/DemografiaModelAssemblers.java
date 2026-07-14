package com.manga.mangas.assemblers;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import com.manga.mangas.Controller.DemografiaController;
import com.manga.mangas.DTO.DemografiaDTO;


@Component
public class DemografiaModelAssemblers implements org.springframework.hateoas.server.RepresentationModelAssembler<DemografiaDTO, EntityModel<DemografiaDTO>> {
    @Override
    public EntityModel<DemografiaDTO> toModel(DemografiaDTO dto) {
        return EntityModel.of(dto,
            linkTo(methodOn(DemografiaController.class).buscarDemografia(dto.getIdDemografia())).withSelfRel()
        );
    }

}
