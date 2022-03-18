package fr.cdamassy2021.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cdamassy2021.entity.Questionnaire;
import fr.cdamassy2021.repository.QuestionRepository;
import fr.cdamassy2021.repository.QuestionnaireRepository;

@Service
@Transactional
public class QuestionnaireService {
	
	@Autowired
	QuestionnaireRepository repo;
	
	public Collection<Questionnaire> listAll() {
		return (Collection<Questionnaire>) repo.findAll();
	}
		
	public Collection<Questionnaire> listQuestionnaireByIdCanal(Long idCanal){
		return repo.findByIdCanal(idCanal);}
}
