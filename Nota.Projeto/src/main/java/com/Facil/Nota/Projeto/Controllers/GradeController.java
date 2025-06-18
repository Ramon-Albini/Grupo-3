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

import com.Facil.Nota.Projeto.DTOs.GradeDTO;
import com.Facil.Nota.Projeto.Services.GradeService;

@RestController
@RequestMapping("/grades")
public class GradeController {
	
	@Autowired
	GradeService gradeService;
	
	@GetMapping("/{id}")
	public GradeDTO findById(@PathVariable Long id) {
		return gradeService.findById(id);
	}
	
	@GetMapping
	public List<GradeDTO> listAllGrades() {
		return gradeService.listAllGrades();
	}
	
	@PostMapping
	public GradeDTO saveGrade(@RequestBody GradeDTO gradeDTO) {
		return gradeService.saveGrade(gradeDTO);
	}
	
	@PutMapping("/{id}")
	public GradeDTO updateGrade(
			@PathVariable Long id, @RequestBody GradeDTO gradeDTO) {
		return gradeService.saveGrade(id, gradeDTO);
	}
	
	@DeleteMapping("/{id}")
	public void deleteGrade(@PathVariable Long id) {
		gradeService.deleteGrade(id);
	}
}
