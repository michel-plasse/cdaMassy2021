package fr.cdamassy2021.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.cdamassy2021.entity.Personne;

public interface PersonneRepository extends CrudRepository<Personne, Long> {

	@Query(value = "SELECT p.id_personne,p.nom,p.prenom\n"
            + "FROM membre_canal mc\n"
            + "		INNER JOIN personne p\n"
            + "			ON mc.id_personne = p.id_personne\n"
            + "WHERE id_canal=2" 
            , nativeQuery = true)
	public Collection<Personne> findMembreByCanal();

}
