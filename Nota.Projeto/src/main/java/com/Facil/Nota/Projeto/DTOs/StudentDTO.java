	package com.Facil.Nota.Projeto.DTOs;
	
	import java.util.List;
	
	import com.Facil.Nota.Projeto.Models.Student;
	
	public record StudentDTO(
			Long id,
			String name,
			List<Long> coursesIds,
			List<Long> gradesIds,
			List<Long> absencesIds,
			Long userId) {
		
		public StudentDTO(Student student) {
			this(
					student.getId(),
					student.getName(),
					student.getCourses().stream()
						.map(course -> course.getId()).toList(),
					student.getGrades().stream()
						.map(grade -> grade.getId()).toList(),
					student.getAbsences().stream()
						.map(absence -> absence.getId()).toList(),
					student.getUser().getId()
					);
		}
	
	}
