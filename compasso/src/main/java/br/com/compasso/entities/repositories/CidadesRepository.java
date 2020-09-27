package br.com.compasso.entities.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.compasso.entities.Cidades;

@Repository
public interface CidadesRepository extends JpaRepository<Cidades, Long> {

	@Query("SELECT obj from Cidades obj where upper(obj.nome) = upper(:nome)")
	List<Cidades> findByName(String nome);

	@Query("SELECT obj from Cidades obj where upper(obj.estado) = upper(:estado)")
	List<Cidades> findByEstado(String estado);
	
	
}
