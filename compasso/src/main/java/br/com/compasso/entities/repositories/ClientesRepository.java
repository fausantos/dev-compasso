package br.com.compasso.entities.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.compasso.entities.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {

	//@Query("SELECT obj from Clientes obj where upper(obj.nomeCompleto) LIKE UPPER('% :nome %') ")
	@Query("SELECT obj from Clientes obj where upper(obj.nomeCompleto) = UPPER(:nome) ")
	List<Clientes> findByName(String nome);
	
}
