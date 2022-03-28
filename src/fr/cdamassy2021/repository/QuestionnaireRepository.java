package fr.cdamassy2021.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.cdamassy2021.entity.Canal;
import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.entity.Questionnaire;

@Repository
public interface QuestionnaireRepository extends CrudRepository<Questionnaire, Long> {

	public List<Questionnaire> findByCanal(Optional<Canal> canal);
	
	public List<Questionnaire> findByCanalAndCreateur(Optional<Canal> canal, Optional<Personne> createur);
	
}
