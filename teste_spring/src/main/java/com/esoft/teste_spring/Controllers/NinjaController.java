package com.esoft.teste_spring.Controllers;

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

import com.esoft.teste_spring.DTOs.NinjaDTO;
import com.esoft.teste_spring.Services.NinjaService;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    @Autowired
    NinjaService ninjaService;

    @GetMapping
    public List<NinjaDTO> listar() {
        return ninjaService.listar();
    }

    @PostMapping
    public NinjaDTO criar(@RequestBody NinjaDTO ninja) throws Exception {
        return ninjaService.salvar(ninja);
    }

    @GetMapping("/{id}")
    public NinjaDTO buscarPorId(@PathVariable Long id) {
        return ninjaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public NinjaDTO atualizar(@PathVariable Long id, @RequestBody NinjaDTO ninja) {
        return ninjaService.salvar(id, ninja);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        ninjaService.deletar(id);
    }

}
