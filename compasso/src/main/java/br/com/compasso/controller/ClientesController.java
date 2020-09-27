package br.com.compasso.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.controller.exception.ObjectNotFoundException;
import br.com.compasso.entities.Clientes;
import br.com.compasso.entities.repositories.ClientesRepository;

@RestController
@RequestMapping("/clientes")
public class ClientesController {
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Clientes save(@RequestBody Clientes cliente) {
		return clientesRepository.save(cliente);
	}	

	
	@GetMapping("/")
	public List<Clientes> findByAll() {
		return clientesRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Clientes findById(@PathVariable Long id){
		Optional<Clientes> obj = clientesRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: " + Clientes.class.getName()));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Clientes> atualizar(@PathVariable Long clienteId, 
			@RequestBody Clientes clientes){
		
		return clientesRepository.findById(clienteId)
				.map(x -> {
					     x.setNomeCompleto(clientes.getNomeCompleto());
				    Clientes update = clientesRepository.save(x);
					
				    return ResponseEntity.ok().body(update);
				}).orElse(ResponseEntity.notFound().build());
			
		
	}
	
	@GetMapping("/nome/{nome}")
	public List<Clientes> findByName(@PathVariable String nome) {
		return clientesRepository.findByName(nome);	
    }
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		
		if (!clientesRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		clientesRepository.deleteById(clienteId);
		
		return ResponseEntity.noContent().build();
		
	}
	

}
