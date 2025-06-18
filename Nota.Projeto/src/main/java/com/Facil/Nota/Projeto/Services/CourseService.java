package com.Facil.Nota.Projeto.Services;

import java.util.List;
import java.util.stream.Collectors;

import com.Facil.Nota.Projeto.DTOs.CourseDTO;
import com.Facil.Nota.Projeto.Exceptions.NotFoundException;
import com.Facil.Nota.Projeto.Models.Course;
import com.Facil.Nota.Projeto.Models.Student;
import com.Facil.Nota.Projeto.Repositories.CourseRepository;
import com.Facil.Nota.Projeto.Repositories.StudentRepository;

import jakarta.transaction.Transactional;

public class CourseService {
	
	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;
	
	public CourseService(
				CourseRepository courseRepository, 
				StudentRepository studentRepository) {
			this.courseRepository = courseRepository;
			this.studentRepository = studentRepository;
		}
	
	public CourseDTO saveCourse(CourseDTO courseDTO) {
		
		Course courseEntity = new Course(courseDTO);
		
		if (courseDTO.studentsIds() != null) {
			List<Student> students = courseDTO.studentsIds()
					.stream()
					.map(ids -> studentRepository.findById(ids)
							.orElseThrow(() -> new NotFoundException("Student not found")))
					.collect(Collectors.toList());
			courseEntity.setStudents(students);
		}
		
		return new CourseDTO(courseRepository.save(courseEntity));
	}
	
	@Transactional
	public CourseDTO saveCourse(Long id, CourseDTO courseDTO) {
		
		courseRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Course not found"));

		Course courseEntity = new Course(courseDTO);
		courseEntity.setId(id);
		
		if (courseDTO.studentsIds() != null) {
			List<Student> students = courseDTO.studentsIds()
					.stream()
					.map(ids -> studentRepository.findById(ids)
							.orElseThrow(() -> new NotFoundException("Student not found")))
					.collect(Collectors.toList());
			courseEntity.setStudents(students);
		}
		
		return new CourseDTO(courseRepository.save(courseEntity));
	}
	
	public CourseDTO findById(Long id) {
		return new CourseDTO(courseRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Course not found!")));
	}
	
	public List<CourseDTO> listAllCourses() {
		return courseRepository
				.findAll()
				.stream()
				.map(course -> new CourseDTO(course))
				.toList();
	}
	
	
	public void deleteCourse(Long id) {
		courseRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Course not found!"));
		courseRepository.deleteById(id);

	}
		
}
