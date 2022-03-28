package fr.cdamassy2021.service;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.cdamassy2021.entity.Canal;
import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.entity.Questionnaire;
import fr.cdamassy2021.repository.CanalRepository;
import fr.cdamassy2021.repository.PersonneRepository;
import fr.cdamassy2021.repository.QuestionnaireRepository;

@Service
@Transactional
public class QuestionnaireService {
	
	@Autowired
	QuestionnaireRepository questionnaireRepository;
	
	@Autowired
	CanalRepository canalRepository;
	
	@Autowired
	PersonneRepository personneRepository;
	
	public ArrayList<Questionnaire> findAllQuestionnaire() {
		
		return (ArrayList<Questionnaire>) questionnaireRepository.findAll();
	}
	
	public ArrayList<Questionnaire> findQuestionnaireByCanal(long idCanal) {
		Optional<Canal> canal = canalRepository.findById(idCanal);
		return (ArrayList<Questionnaire>) questionnaireRepository.findByCanal(canal);
	}
	
	public ArrayList<Questionnaire> findQuestionnaireByCanalAndCreateur(long idCanal, long idCreateur) {
		Optional<Canal> canal = canalRepository.findById(idCanal);
		Optional<Personne> createur = personneRepository.findById(idCreateur);
		return (ArrayList<Questionnaire>) questionnaireRepository.findByCanalAndCreateur(canal, createur);
	}

		

}
