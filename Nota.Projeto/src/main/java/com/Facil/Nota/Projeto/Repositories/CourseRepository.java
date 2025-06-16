package com.Facil.Nota.Projeto.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Facil.Nota.Projeto.Models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

	List<Course> findByNameContainingIgnoreCase(String name);
}
