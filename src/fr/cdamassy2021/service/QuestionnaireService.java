package fr.cdamassy2021.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.cdamassy2021.dto.QuestionnaireDto;
import fr.cdamassy2021.entity.Questionnaire;
import fr.cdamassy2021.repository.QuestionnaireRepository;

@Service
@Transactional
public class QuestionnaireService {
	
	@Autowired
	QuestionnaireRepository questionnaireRepository;
	
	public ArrayList<Questionnaire> findAllQuestionnaire() {
		
		return (ArrayList<Questionnaire>) questionnaireRepository.findAll();
	}
		

}
