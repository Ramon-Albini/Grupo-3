package com.esoft.teste_spring.DTOs;

import java.util.List;

import com.esoft.teste_spring.models.Ninja;

public record NinjaDTO(
        Long id,
        String nome,
        int idade,
        String cla,
        Long vilaId,
        Long missaoId,
        List<Long> jutsuIds) {

    public NinjaDTO(Ninja ninja) {
        this(
                ninja.getId(),
                ninja.getNome(),
                ninja.getIdade(),
                ninja.getCla(),
                ninja.getVila() != null ? ninja.getVila().getId() : null,
                ninja.getMissao() != null ? ninja.getMissao().getId() : null,
        		ninja.getJutsus().stream()
        			.map(jutsu -> jutsu.getId()).toList());
    }
}