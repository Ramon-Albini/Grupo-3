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

import com.Facil.Nota.Projeto.DTOs.AbsenceDTO;
import com.Facil.Nota.Projeto.Services.AbsenceService;

@RestController
@RequestMapping("/absences")
public class AbsenceController {
	
	@Autowired
	AbsenceService absenceService;
	
	@GetMapping("/{id}")
	public AbsenceDTO findById(@PathVariable Long id) {
		return absenceService.findById(id);
	}
	
	@GetMapping
	public List<AbsenceDTO> listAllAbsences() {
		return absenceService.listAllAbsences();
	}
	
	@PostMapping
	public AbsenceDTO saveAbsence(@RequestBody AbsenceDTO absenceDTO) {
		return absenceService.saveAbsence(absenceDTO);
	}
	
	@PutMapping("/{id}")
	public AbsenceDTO updateAbsence(
			@PathVariable Long id, @RequestBody AbsenceDTO absenceDTO) {
		return absenceService.saveAbsence(id, absenceDTO);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAbsence(@PathVariable Long id) {
		absenceService.deleteAbsence(id);
	}
}