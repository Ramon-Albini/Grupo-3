package com.Facil.Nota.Projeto.Services;

import java.util.List;

import com.Facil.Nota.Projeto.DTOs.AbsenceDTO;
import com.Facil.Nota.Projeto.Exceptions.NotFoundException;
import com.Facil.Nota.Projeto.Models.Absence;
import com.Facil.Nota.Projeto.Models.Course;
import com.Facil.Nota.Projeto.Models.Student;
import com.Facil.Nota.Projeto.Repositories.AbsenceRepository;
import com.Facil.Nota.Projeto.Repositories.CourseRepository;
import com.Facil.Nota.Projeto.Repositories.StudentRepository;

import jakarta.transaction.Transactional;

public class AbsenceService {
	
	private final StudentRepository studentRepository;
	private final CourseRepository courseRepository;
	private final AbsenceRepository absenceRepository;

	public AbsenceService( 
			StudentRepository studentRepository,
			CourseRepository courseRepository,
			AbsenceRepository absenceRepository
			) {
		
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
		this.absenceRepository = absenceRepository;
	}
	
	public AbsenceDTO saveAbsence(AbsenceDTO absenceDTO) {
		
		Absence absenceEntity = new Absence(absenceDTO);
		
		if (absenceDTO.courseId() != null ) {
			Course course = courseRepository.findById(absenceDTO.courseId())
					.orElseThrow(() -> new NotFoundException("Course not found"));
			absenceEntity.setCourse(course);
		}
		
		if (absenceDTO.studentId() != null ) {
			Student student = studentRepository.findById(absenceDTO.studentId())
					.orElseThrow(() -> new NotFoundException("Student not found"));
			absenceEntity.setStudent(student);
		}
		
		return new AbsenceDTO(absenceRepository.save(absenceEntity));
		
	}
	
	@Transactional
	public AbsenceDTO saveAbsence(Long id, AbsenceDTO absenceDTO) {
		absenceRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Absence not found"));
		Absence absenceEntity = new Absence(absenceDTO);
		absenceEntity.setId(id);
		
		if (absenceDTO.courseId() != null ) {
			Course course = courseRepository.findById(absenceDTO.courseId())
					.orElseThrow(() -> new NotFoundException("Course not found"));
			absenceEntity.setCourse(course);
		}
		
		if (absenceDTO.studentId() != null ) {
			Student student = studentRepository.findById(absenceDTO.studentId())
					.orElseThrow(() -> new NotFoundException("Student not found"));
			absenceEntity.setStudent(student);
		}
		
		return new AbsenceDTO(absenceRepository.save(absenceEntity));
	}
	
	public List<AbsenceDTO> listAllAbsences() {
		return absenceRepository
				.findAll()
				.stream()
				.map(absence -> new AbsenceDTO(absence))
				.toList();
	}
	
	public AbsenceDTO findById(Long id) {
		return new AbsenceDTO(absenceRepository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Absence not found")));
	}
	
	public void deleteAbsence(Long id) {
		absenceRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Absence not found"));
		absenceRepository.deleteById(id);
	}
}
