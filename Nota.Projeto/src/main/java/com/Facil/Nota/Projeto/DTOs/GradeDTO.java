package com.Facil.Nota.Projeto.DTOs;

import java.util.Date;

import com.Facil.Nota.Projeto.Models.Grade;

public record GradeDTO(
		Long id,
		double value,
		Date date,
		String description,
		Long courseId,
		Long studentId) {
	
	public GradeDTO(Grade grade) {
		this(
				grade.getId(),
				grade.getValue(),
				grade.getDate(),
				grade.getDescription(),
				grade.getCourse() != null ? grade.getCourse().getId() : null,
				grade.getStudent() != null ? grade.getStudent().getId() : null				
				);
	}

}
