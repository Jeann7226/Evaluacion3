package com.autor.autores.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autor.autores.Model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer>{
    
    

}
