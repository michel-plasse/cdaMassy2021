package fr.cdamassy2021.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.cdamassy2021.entity.EFG;


public interface EFGRepository extends CrudRepository<EFG, Long> {

	public List<EFG> findAll();
	
}
