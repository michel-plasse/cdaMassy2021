package fr.cdamassy2021.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.cdamassy2021.entity.Personne;

@Repository
public interface PersonneRepository extends CrudRepository<Personne, Long> {
	
	@Query(value = "SELECT p FROM Personne p WHERE p.email = ?1 "
			+ " AND p.pwd = ?2 ")
	public Personne exist(String email, String pwd);
	
}