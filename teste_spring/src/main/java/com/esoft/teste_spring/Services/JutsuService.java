
package com.esoft.teste_spring.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esoft.teste_spring.DTOs.JutsuDTO;
import com.esoft.teste_spring.Exceptions.NaoEncontradoException;
import com.esoft.teste_spring.models.Jutsu;
import com.esoft.teste_spring.models.Ninja;
import com.esoft.teste_spring.repositories.JutsuRepository;
import com.esoft.teste_spring.repositories.NinjaRepository;

import jakarta.transaction.Transactional;

@Service
public class JutsuService {
	private final JutsuRepository jutsuRepository;
	private final NinjaRepository ninjaRepository;
	
	public JutsuService(JutsuRepository jutsuRepository, NinjaRepository ninjaRepository) {
		this.jutsuRepository = jutsuRepository;
		this.ninjaRepository = ninjaRepository;
	}
	
	public List<JutsuDTO> listar(){
		return jutsuRepository.findAll().stream()
					.map(jutsu -> new JutsuDTO(jutsu)).toList();
	}
	
	public JutsuDTO salvar(JutsuDTO jutsu) {
		Jutsu jutsuEntity = new Jutsu(jutsu);
		
		List<Ninja> ninjas = ninjaRepository.findAllById(jutsu.ninjaIds());
		jutsuEntity.setNinjas(ninjas);
		ninjas.forEach(n -> n.getJutsus().add(jutsuEntity));
		
		return new JutsuDTO(jutsuRepository.save(jutsuEntity));		
	}
	
	@Transactional
	public JutsuDTO salvar(Long id, JutsuDTO jutsu) {
		jutsuRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("Jutsu com id "+id+" não foi encontrado"));
		Jutsu jutsuEntity = new Jutsu(jutsu);
		
		List<Ninja> ninjas = ninjaRepository.findAllById(jutsu.ninjaIds());
		jutsuEntity.setNinjas(ninjas);
		ninjas.forEach(n -> n.getJutsus().add(jutsuEntity));
		
		jutsuEntity.setId(id);
		
		return new JutsuDTO(jutsuRepository.save(jutsuEntity));
	}
	
	public JutsuDTO buscarPorId(Long id) {
		return new JutsuDTO(jutsuRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("Jutsu com id "+id+" não foi encontrado")));
	}
	
	public void deletar(Long id) {
		jutsuRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("Jutsu com id "+id+" não foi encontrado"));
		jutsuRepository.deleteById(id);
	}
	
	@Transactional
	public JutsuDTO adicionarNinja(Long jutsuId, Long ninjaId) {
		Jutsu jutsu = jutsuRepository.findById(jutsuId)
				.orElseThrow(() -> new NaoEncontradoException("Jutsu com id "+jutsuId+" não foi encontrado"));
		
		Ninja ninja = jutsu.getNinjas().stream()
				.filter(n -> n.getId()
						.equals(ninjaId))
				.findFirst()
				.orElse(null);
		
		if(ninja == null ) {
			ninja = ninjaRepository.findById(ninjaId)
					.orElseThrow(() -> new NaoEncontradoException("Ninja com id " + ninjaId + " não encontrado"));
			jutsu.getNinjas().add(ninja);
		}
		
		return new JutsuDTO(jutsuRepository.save(jutsu));
	}
	
	@Transactional
	public JutsuDTO removerNinja(Long jutsuId, Long ninjaId) {
		Jutsu jutsu = jutsuRepository.findById(jutsuId)
		        .orElseThrow(() -> new NaoEncontradoException("Jutsu com id " + jutsuId + " não encontrado"));
		
		jutsu.getNinjas().removeIf(n -> n.getId().equals(ninjaId));
		
		return new JutsuDTO(jutsuRepository.save(jutsu));
	}
	
	
	
	
	
	
	
	
	
	
	
}
