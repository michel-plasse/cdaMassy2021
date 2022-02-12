package fr.cdamassy2021.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cdamassy2021.entity.Questionnaire;
import fr.cdamassy2021.repository.QuestionnaireRepository;


@Service
@Transactional
public class QuestionnaireService {

	@Autowired
	QuestionnaireRepository repo;
	
	public void save(Questionnaire questionnaire) {
		repo.save(questionnaire);
	}
	
	public List<Questionnaire> listAll() {
		return (List<Questionnaire>) repo.findAll();
	}
	
	
}
