package com.autor.autores.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autor.autores.Model.Origen;

@Repository
public interface OrigenRepository extends JpaRepository<Origen, Integer>{

}
