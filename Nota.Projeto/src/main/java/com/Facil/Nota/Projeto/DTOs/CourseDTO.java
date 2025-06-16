package com.Facil.Nota.Projeto.DTOs;

import java.util.List;

import com.Facil.Nota.Projeto.Models.Course;

public record CourseDTO(
		Long id,
		String name,
		List<Long> studentsIds) {
	public CourseDTO(Course course) {
		this(
				course.getId(),
				course.getName(),
				course.getStudents().stream()
					.map(student -> student.getId()).toList()
				);
	}

}
