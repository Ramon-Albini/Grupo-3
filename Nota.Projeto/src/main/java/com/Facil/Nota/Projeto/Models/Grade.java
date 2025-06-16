package com.Facil.Nota.Projeto.Models;

import java.util.Date;

import com.Facil.Nota.Projeto.DTOs.GradeDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "grades") //entidade NOTAS
public class Grade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private double value;
	private Date date;
	@Column(nullable = false)
	private String description;
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
	
	public Grade(GradeDTO gradeDTO) {
		this.id = gradeDTO.id();
		this.value = gradeDTO.value();
		this.date = gradeDTO.date();
		this.description = gradeDTO.description();
	}
	
}
