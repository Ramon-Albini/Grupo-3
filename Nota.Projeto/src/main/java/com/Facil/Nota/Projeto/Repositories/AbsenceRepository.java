package com.Facil.Nota.Projeto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Facil.Nota.Projeto.Models.Absence;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long>{

}
