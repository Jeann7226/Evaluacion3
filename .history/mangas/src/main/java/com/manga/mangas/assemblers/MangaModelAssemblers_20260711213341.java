package com.manga.mangas.assemblers;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.manga.mangas.controller.MangaController;
import com.manga.mangas.model.Manga;

@Component
public class MangaModelAssemblers implements RepresentationModelAssembler<Manga, EntityModel<Manga>> {
    @Override
    public EntityModel<Manga> toModel(Manga manga) {
        return EntityModel.of(manga,
            linkTo(methodOn(MangaController.class).buscarMangaId(manga.getIdManga())).withSelfRel()
    );
    }
}
