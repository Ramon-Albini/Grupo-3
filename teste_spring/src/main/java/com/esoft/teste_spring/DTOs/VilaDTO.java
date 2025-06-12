package com.esoft.teste_spring.DTOs;

import java.util.List;

import com.esoft.teste_spring.models.Ninja;
import com.esoft.teste_spring.models.Vila;

public record VilaDTO(
		Long id, 
		String nome, 
		Long qtdeHabitantes, 
		List<Ninja> ninjas) {
	
	public VilaDTO(Vila vila) {
		this(
				vila.getId(),
				vila.getNome(),
				vila.getQtdeHabitantes(),
				vila.getNinjas());
	}
}
