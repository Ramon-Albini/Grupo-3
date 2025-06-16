package com.Facil.Nota.Projeto.DTOs;

import java.util.Date;

import com.Facil.Nota.Projeto.Models.Absence;

public record AbsenceDTO(
		Long id,
		Date date,
		String reason,
		Long courseId,
		Long studentId
		) {
	public AbsenceDTO(Absence absence) {
		this(
				absence.getId(),
				absence.getDate(),
				absence.getReason(),
				absence.getCourse() != null ? absence.getCourse().getId() : null,
				absence.getStudent() != null ? absence.getStudent().getId() : null
						);
	}

}
