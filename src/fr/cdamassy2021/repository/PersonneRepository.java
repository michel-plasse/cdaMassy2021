package fr.cdamassy2021.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cdamassy2021.entity.Personne;

@Repository
public interface PersonneRepository extends CrudRepository<Personne, Long> {
	
	@Query(value = "SELECT p FROM Personne p WHERE p.nom LIKE '%' || :keyword || '%'")
	public List<Personne> search(@Param("keyword") String keyword);
}