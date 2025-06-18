package com.Facil.Nota.Projeto.Models;

import java.util.Date;

import com.Facil.Nota.Projeto.DTOs.AbsenceDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "absences") //entidade FALTA
public class Absence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Date date;
	private String reason;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "course_id")
	private Course course;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "student_id")
	private Student student;
	
	public Absence(AbsenceDTO absenceDTO) {
		this.id = absenceDTO.id();
		this.date = absenceDTO.date();
		this.reason = absenceDTO.reason();
	}
}
