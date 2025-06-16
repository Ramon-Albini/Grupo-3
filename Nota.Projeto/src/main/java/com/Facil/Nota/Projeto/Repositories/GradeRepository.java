package com.Facil.Nota.Projeto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Facil.Nota.Projeto.Models.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

}
