package com.Facil.Nota.Projeto.Models;

import com.Facil.Nota.Projeto.DTOs.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users") //entidade USUARIOS
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String login;
	@Column(nullable = false)
	private String password;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "student_id", referencedColumnName = "id")
	private Student student;
	
	public User(UserDTO userDTO) {
		this.id = userDTO.id();
		this.login = userDTO.login();
		this.password = userDTO.password();
	}
}
