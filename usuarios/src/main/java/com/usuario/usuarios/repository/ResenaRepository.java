package com.usuario.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usuario.usuarios.model.Resena;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {

}
