package com.Facil.Nota.Projeto.Models;

import java.util.ArrayList;
import java.util.List;

import com.Facil.Nota.Projeto.DTOs.StudentDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "students")//entidade ESTUDANTE	
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(nullable = false)
	String name;
	
	@ManyToMany
	@JsonIgnore
	@JoinTable(
			name = "student_course",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "course_id")
			)
	List<Course> courses = new ArrayList<>();
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Grade> grades = new ArrayList<>();
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Absence> absences = new ArrayList<>();
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Student(StudentDTO studentDTO) {
		this.id = studentDTO.id();
		this.name = studentDTO.name();
	}
	
}
