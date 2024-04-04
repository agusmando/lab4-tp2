package com.TPGrupalLab4.JavaPostgre.repository;

import com.TPGrupalLab4.JavaPostgre.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {
}
