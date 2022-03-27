package fr.cdamassy2021.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.cdamassy2021.entity.Questionnaire;

@Repository
public interface QuestionnaireRepository extends CrudRepository<Questionnaire, Long> {
	
	
}
