package br.com.compasso.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.entities.Cidades;
import br.com.compasso.entities.repositories.CidadesRepository;

@RestController
@RequestMapping("/cidades")
public class CidadesController {
	
	@Autowired
	private CidadesRepository cidadesRepository;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidades save(@RequestBody Cidades cliente) {
		return cidadesRepository.save(cliente);
	}	
	@GetMapping("/{nome}")
	public List<Cidades> findByName(@PathVariable String nome) {
		return cidadesRepository.findByName(nome);	
    }
	
	@GetMapping("/")
	public List<Cidades> findByAll() {
		return cidadesRepository.findAll();
	}
	
	@GetMapping("/estado/{estado}")
	public List<Cidades> findByEstado(@PathVariable String estado){
		return cidadesRepository.findByEstado(estado);
	}
}
