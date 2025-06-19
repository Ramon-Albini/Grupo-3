package com.Facil.Nota.Projeto.Models;

import java.util.ArrayList;
import java.util.List;

import com.Facil.Nota.Projeto.DTOs.CourseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")//entidade materia
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	
	@ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Student> students = new ArrayList<>();	
	
	public Course(CourseDTO courseDTO) {
		this.id = courseDTO.id();
		this.name = courseDTO.name();
	}
}
