package com.Facil.Nota.Projeto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Facil.Nota.Projeto.DTOs.StudentDTO;
import com.Facil.Nota.Projeto.Services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@GetMapping("/{id}")
	public StudentDTO findById(@PathVariable Long id) {
		return studentService.findById(id);
	}
	
	@PostMapping
	public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO) {
		return studentService.saveStudent(studentDTO);
	}
	
	@PutMapping("/{id}")
	public StudentDTO updateStudent(
			@PathVariable Long id, 
			@RequestBody StudentDTO studentDTO) {
		
		return studentService.saveStudent(id, studentDTO);
	}
	
	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
	}
	
}
