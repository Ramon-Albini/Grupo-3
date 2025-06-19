package com.Facil.Nota.Projeto.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Facil.Nota.Projeto.DTOs.UserDTO;
import com.Facil.Nota.Projeto.Exceptions.NotFoundException;
import com.Facil.Nota.Projeto.Models.Student;
import com.Facil.Nota.Projeto.Models.User;
import com.Facil.Nota.Projeto.Repositories.StudentRepository;
import com.Facil.Nota.Projeto.Repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final StudentRepository studentRepository;

	
	public UserService(UserRepository userRepository, StudentRepository studentRepository) {
		this.userRepository = userRepository;
		this.studentRepository = studentRepository;
	}
	
	public UserDTO saveUser(UserDTO userDTO) {
		
		User userEntity = new User(userDTO);
		
		if (userDTO.studentId() != null ) {
			Student student = studentRepository.findById(userDTO.studentId())
					.orElseThrow(() -> new NotFoundException("Student not found"));
			userEntity.setStudent(student);
		}	
		
		return new UserDTO(userRepository.save(userEntity));
	}
	
	@Transactional
	public UserDTO saveUser(Long id, UserDTO userDTO) {
		userRepository.findById(id)
					.orElseThrow(() -> new NotFoundException("User not found!!"));
	
		User userEntity = new User(userDTO);
		userEntity.setId(id);
		
		return new UserDTO(userRepository.save(userEntity));	
	}
	
	public List<UserDTO> listAllUsers(){
		return userRepository.findAll()
				.stream().map(user -> new UserDTO(user
						)).toList();
	}

	public UserDTO findById(Long id) {
		return new UserDTO(userRepository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("User not found!!!")));
	}
	
	public void delete(Long id) {
		userRepository.findById(id)
		.orElseThrow(() -> new NotFoundException("User not found!!"));
		userRepository.deleteById(id);
	}
}
