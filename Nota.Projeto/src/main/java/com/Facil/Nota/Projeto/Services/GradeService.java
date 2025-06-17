package com.Facil.Nota.Projeto.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Facil.Nota.Projeto.DTOs.GradeDTO;
import com.Facil.Nota.Projeto.Exceptions.NotFoundException;
import com.Facil.Nota.Projeto.Models.Course;
import com.Facil.Nota.Projeto.Models.Grade;
import com.Facil.Nota.Projeto.Models.Student;
import com.Facil.Nota.Projeto.Repositories.CourseRepository;
import com.Facil.Nota.Projeto.Repositories.GradeRepository;
import com.Facil.Nota.Projeto.Repositories.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class GradeService {
	
	private final GradeRepository gradeRepository;
	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;
	
	public GradeService(
			GradeRepository gradeRepository,
			CourseRepository courseRepository,
			StudentRepository studentRepository) {
		
		this.gradeRepository = gradeRepository;
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
	}
	
	public GradeDTO saveGrade(GradeDTO gradeDTO) {
		
		Grade gradeEntity = new Grade(gradeDTO);
		
		if (gradeDTO.courseId() != null) {
			Course course = courseRepository.findById(gradeDTO.courseId())
					.orElseThrow(() -> new NotFoundException("Course not found"));
			gradeEntity.setCourse(course);
		}
		
		if (gradeDTO.studentId() != null) {
			Student student = studentRepository.findById(gradeDTO.studentId())
					.orElseThrow(() -> new NotFoundException("Student not found"));
			gradeEntity.setStudent(student);
		}
		
		return new GradeDTO(gradeRepository.save(gradeEntity));		
	}
	
	@Transactional
	public GradeDTO saveGrade(Long id, GradeDTO gradeDTO) {
		
		gradeRepository.findById(id)
		.orElseThrow(() -> new NotFoundException("Grade not found"));
		Grade gradeEntity = new Grade(gradeDTO);
		gradeEntity.setId(id);
		
		if (gradeDTO.courseId() != null) {
			Course course = courseRepository.findById(gradeDTO.courseId())
					.orElseThrow(() -> new NotFoundException("Course not found"));
			gradeEntity.setCourse(course);
		}
		
		if (gradeDTO.studentId() != null) {
			Student student = studentRepository.findById(gradeDTO.studentId())
					.orElseThrow(() -> new NotFoundException("Student not found"));
			gradeEntity.setStudent(student);
		}
		
		return new GradeDTO(gradeRepository.save(gradeEntity));		
	}
	
	public List<GradeDTO> listAllGrades(){
		return gradeRepository.findAll()
				.stream().map(grade -> new GradeDTO(grade)).toList();
	}
	
	public GradeDTO findById(Long id) {
		return new GradeDTO(gradeRepository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Grade not found")));
	}
	
	public void deleteGrade(Long id) {
		gradeRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Grade not found"));
		gradeRepository.deleteById(id);
	}
}
