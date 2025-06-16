package com.Facil.Nota.Projeto.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Facil.Nota.Projeto.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findByLoginContainingIgnoreCase(String name);
}
