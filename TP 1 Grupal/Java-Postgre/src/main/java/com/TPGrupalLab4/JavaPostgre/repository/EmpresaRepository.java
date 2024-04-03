package com.TPGrupalLab4.JavaPostgre.repository;

import com.TPGrupalLab4.JavaPostgre.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
}
