package com.esoft.teste_spring.models;

import java.util.List;

import com.esoft.teste_spring.DTOs.JutsuDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "tb_jutsu")
public class Jutsu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String descricao;
	@Column(nullable = false)
	private String dificuldade;
	@ManyToMany(mappedBy = "jutsus")
    private List<Ninja> ninjas;
	
	public Jutsu(JutsuDTO jutsu) {
		this.id = jutsu.id();
		this.nome = jutsu.nome();
		this.descricao = jutsu.descricao();
		this.dificuldade = jutsu.dificuldade();
	}
}
