package fr.cdamassy2021.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cdamassy2021.entity.Proposition;

@Repository
public interface PropositionRepository extends CrudRepository<Proposition, Long> {
	
	@Query(value = "SELECT p FROM Proposition p WHERE p.libelle LIKE '%' || :keyword || '%'")
	public List<Proposition> search(@Param("keyword") String keyword);
}