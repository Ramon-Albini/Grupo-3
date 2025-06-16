package com.Facil.Nota.Projeto.DTOs;

import com.Facil.Nota.Projeto.Models.User;

public record UserDTO(
		Long id,
		String login,
		String passwaord,
		Long studentId) {
	
	public UserDTO(User user) {
		this(
				user.getId(),
				user.getLogin(),
				user.getPasswaord(),
				user.getStudent() != null ? user.getStudent().getId() : null
				);
	}
	

}
