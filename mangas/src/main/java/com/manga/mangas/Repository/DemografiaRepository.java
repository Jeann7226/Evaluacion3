package com.manga.mangas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manga.mangas.Model.Demografia;

@Repository
public interface DemografiaRepository extends JpaRepository<Demografia, Integer> {

}
