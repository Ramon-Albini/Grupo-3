package com.Facil.Nota.Projeto.Services;

import org.springframework.stereotype.Service;

import com.Facil.Nota.Projeto.DTOs.StudentDTO;
import com.Facil.Nota.Projeto.Models.Student;
import com.Facil.Nota.Projeto.Repositories.AbsenceRepository;
import com.Facil.Nota.Projeto.Repositories.CourseRepository;
import com.Facil.Nota.Projeto.Repositories.GradeRepository;
import com.Facil.Nota.Projeto.Repositories.StudentRepository;
import com.Facil.Nota.Projeto.Repositories.UserRepository;

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
		
		if ()
	}
}
