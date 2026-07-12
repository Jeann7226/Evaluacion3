package com.autor.autores.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.autor.autores.DTO.GeneroDTO;
import com.autor.autores.Model.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer>{
    
<<<<<<< HEAD
    @Query("SELECT new com.autor.autores.Model.GeneroDTO(g.idGenero, g.generoTematico) FROM Genero g")
=======
    @Query("SELECT new com.autor.autores.DTO.GeneroDTO(g.idGenero, g.generoTematico) FROM Genero g")
>>>>>>> main
    List<GeneroDTO> findAllGeneroDTOs();

}
