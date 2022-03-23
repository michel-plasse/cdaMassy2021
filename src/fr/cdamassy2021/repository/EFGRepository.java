package fr.cdamassy2021.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.cdamassy2021.entity.EFG;
import fr.cdamassy2021.entity.Personne;


public interface EFGRepository extends CrudRepository<EFG, Integer> {

	
	@Query(value = "SELECT e FROM EFG e WHERE e.createur.idCanal=?1")	
	public Optional<List<EFG>>findByCanal(int idCanal);
	
	 
	
	@Query(value="SELECT COUNT(m) FROM MembreCanal m WHERE id_canal = ?1")
	public int membresCanal(int idCanal);
	
}
