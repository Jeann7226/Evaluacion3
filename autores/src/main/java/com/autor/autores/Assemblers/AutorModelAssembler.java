package com.autor.autores.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.autor.autores.Controller.AutorController;
import com.autor.autores.Model.Autor;

public class AutorModelAssembler implements RepresentationModelAssembler<Autor, EntityModel<Autor>> {
    @Override
    public EntityModel<Autor> toModel(Autor autor) {
        return EntityModel.of(autor,
            linkTo(methodOn(AutorController.class).porId(autor.getIdAutor())).withSelfRel()
        );
    }
}
