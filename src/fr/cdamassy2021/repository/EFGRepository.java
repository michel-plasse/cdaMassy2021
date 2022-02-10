package fr.cdamassy2021.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.cdamassy2021.entity.EFG;


public interface EFGRepository extends CrudRepository<EFG, Long> {

	public List<EFG> findAll();
	
	@Query(value = "SELECT e FROM EFG e WHERE e.idCanal=?1")	
	public List<EFG> findByCanal(int idCanal);

}
