package com.Facil.Nota.Projeto.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Facil.Nota.Projeto.DTOs.CourseDTO;
import com.Facil.Nota.Projeto.Services.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@GetMapping
	public List<CourseDTO> listAllCourses() {
		return courseService.listAllCourses();
	}
	
	@GetMapping("/{id}")
	public CourseDTO findById(@PathVariable Long id) {
		return courseService.findById(id);
	}
	
	@PostMapping
	public CourseDTO saveCourse(@RequestBody CourseDTO courseDTO) {
		return courseService.saveCourse(courseDTO);
	}
	
	@PutMapping("/{id}")
	public CourseDTO updateCourse(
			@PathVariable Long id, @RequestBody CourseDTO CourseDTO) {
		return courseService.saveCourse(id, CourseDTO);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCourse(@PathVariable Long id) {
		courseService.deleteCourse(id);
	}
}
