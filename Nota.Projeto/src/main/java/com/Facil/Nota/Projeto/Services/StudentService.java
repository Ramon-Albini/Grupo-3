package com.Facil.Nota.Projeto.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Facil.Nota.Projeto.DTOs.StudentDTO;
import com.Facil.Nota.Projeto.Exceptions.NotFoundException;
import com.Facil.Nota.Projeto.Models.Absence;
import com.Facil.Nota.Projeto.Models.Course;
import com.Facil.Nota.Projeto.Models.Grade;
import com.Facil.Nota.Projeto.Models.Student;
import com.Facil.Nota.Projeto.Models.User;
import com.Facil.Nota.Projeto.Repositories.AbsenceRepository;
import com.Facil.Nota.Projeto.Repositories.CourseRepository;
import com.Facil.Nota.Projeto.Repositories.GradeRepository;
import com.Facil.Nota.Projeto.Repositories.StudentRepository;
import com.Facil.Nota.Projeto.Repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
	
	private final StudentRepository studentRepository;
	private final CourseRepository courseRepository;
	private final GradeRepository gradeRepository;
	private final AbsenceRepository absenceRepository;
	private final UserRepository userRepository;
	
	public StudentService( 
			StudentRepository studentRepository,
			CourseRepository courseRepository,
			GradeRepository gradeRepository,
			AbsenceRepository absenceRepository,
			UserRepository userRepository
			) {
		
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
		this.gradeRepository = gradeRepository;
		this.absenceRepository = absenceRepository;
		this.userRepository = userRepository;
	}
	
	public StudentDTO saveStudent(StudentDTO studentDTO) {
		
		Student studentEntity = new Student(studentDTO);
		
		if (studentDTO.coursesIds() != null) {
			List<Course> courses = studentDTO.coursesIds()
					.stream()
					.map(ids -> courseRepository.findById(ids)
							.orElseThrow(() -> new NotFoundException("Course not found")))
					.collect(Collectors.toList());
			studentEntity.setCourses(courses);
		}
		
		if (studentDTO.gradesIds() != null) {
			List<Grade> grades = studentDTO.gradesIds()
					.stream()
					.map(ids -> gradeRepository.findById(ids)
							.orElseThrow(() -> new NotFoundException("Grade not found")))
					.collect(Collectors.toList());
			studentEntity.setGrades(grades);
		}
		
		if (studentDTO.absencesIds() != null) {
			List<Absence> absenses = studentDTO.absencesIds()
					.stream()
					.map(ids -> absenceRepository.findById(ids)
							.orElseThrow(() -> new NotFoundException("Absense not found")))
					.collect(Collectors.toList());
			studentEntity.setAbsences(absenses);
		}
		
		if (studentDTO.userId() != null ) {
			User user = userRepository.findById(studentDTO.userId())
					.orElseThrow(() -> new NotFoundException("User not found"));
			studentEntity.setUser(user);
		}
		
		return new StudentDTO(studentRepository.save(studentEntity));
		
	}
	
	@Transactional
	public StudentDTO saveStudent(Long id, StudentDTO studentDTO) {
		studentRepository.findById(id)
						.orElseThrow(() -> new NotFoundException("Student not found"));
		
		Student studentEntity = new Student(studentDTO);
		studentEntity.setId(id);
		
		if (studentDTO.coursesIds() != null) {
			List<Course> courses = studentDTO.coursesIds()
					.stream()
					.map(ids -> courseRepository.findById(ids)
							.orElseThrow(() -> new NotFoundException("Course not found")))
					.collect(Collectors.toList());
			studentEntity.setCourses(courses);
		}
		
		if (studentDTO.gradesIds() != null) {
			List<Grade> grades = studentDTO.gradesIds()
					.stream()
					.map(ids -> gradeRepository.findById(ids)
							.orElseThrow(() -> new NotFoundException("Grade not found")))
					.collect(Collectors.toList());
			studentEntity.setGrades(grades);
		}
		
		if (studentDTO.absencesIds() != null) {
			List<Absence> absenses = studentDTO.absencesIds()
					.stream()
					.map(ids -> absenceRepository.findById(ids)
							.orElseThrow(() -> new NotFoundException("Absense not found")))
					.collect(Collectors.toList());
			studentEntity.setAbsences(absenses);
		}
		
		if (studentDTO.userId() != null ) {
			User user = userRepository.findById(studentDTO.userId())
					.orElseThrow(() -> new NotFoundException("User not found"));
			studentEntity.setUser(user);
		}
		
		return new StudentDTO(studentRepository.save(studentEntity));
		
	}
	
	public StudentDTO findById(Long id) {
		return new StudentDTO(studentRepository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Student not found")));
	}
	
	public void deleteStudent(Long id) {
		studentRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Student not found"));
		studentRepository.deleteById(id);
	}
}
