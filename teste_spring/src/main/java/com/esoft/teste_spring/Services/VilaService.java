package com.esoft.teste_spring.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esoft.teste_spring.DTOs.VilaDTO;
import com.esoft.teste_spring.Exceptions.NaoEncontradoException;
import com.esoft.teste_spring.models.Vila;
import com.esoft.teste_spring.repositories.VilaRepository;

import jakarta.transaction.Transactional;

@Service
public class VilaService {

	private final VilaRepository vilaRepository;

	public VilaService(VilaRepository vilaRepository) {
		this.vilaRepository = vilaRepository;
	}

	public List<VilaDTO> listar() {
		return vilaRepository.findAll().stream()
					.map(vila -> new VilaDTO(vila)).toList();
	}

	public VilaDTO salvar(VilaDTO vila) {
		Vila vilaEntity = new Vila(vila);

		return new VilaDTO(vilaRepository.save(vilaEntity));
	}

	@Transactional
	public VilaDTO salvar(Long id, VilaDTO vila) {
		vilaRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("Vila com id " + id + " não foi encontrado!"));
		Vila vilaEntity = new Vila(vila);
		vilaEntity.setId(id);

		return new VilaDTO(vilaRepository.save(vilaEntity));
	}

	public VilaDTO buscarPorId(Long id) {
		return new VilaDTO(vilaRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("Vila com id " + id + " não foi encontrado!")));
	}

	public void deletar(Long id) {
		vilaRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("Vila com id " + id + " não foi encontrado!"));
		vilaRepository.deleteById(id);
	}
}
