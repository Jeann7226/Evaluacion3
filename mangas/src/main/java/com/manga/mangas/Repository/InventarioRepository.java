package com.manga.mangas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manga.mangas.Model.Inventario;
@Repository
public interface InventarioRepository extends JpaRepository <Inventario, Integer>{

}
