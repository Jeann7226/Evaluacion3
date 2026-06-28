package com.manga.mangas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manga.mangas.Model.Manga;

public interface MangaRepository extends JpaRepository<Manga, Integer> {
    @Query("SELECT m FROM Manga m WHERE m.precio <= :precioMax")
    List<Manga> buscarPorPrecioMaximo(@Param("precioMax") Integer precioMax);

}
