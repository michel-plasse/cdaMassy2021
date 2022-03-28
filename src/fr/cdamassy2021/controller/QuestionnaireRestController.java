package fr.cdamassy2021.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.cdamassy2021.dto.QuestionnaireDto;
import fr.cdamassy2021.service.QuestionnaireService;

/**
 * 
 * @Author : Groupe 2 (Christian, Melhem)
 * 
 * */

@RestController
public class QuestionnaireRestController {
	
	@Autowired
	private QuestionnaireService questionnaireService;
		
	
	@RequestMapping("/api/questionnaire")
	public List<QuestionnaireDto> listeTousLesQuestionnaires() {	
		ArrayList<QuestionnaireDto> questionnaireDtos = new ArrayList<QuestionnaireDto>();
		questionnaireService.findAllQuestionnaire().forEach( q -> questionnaireDtos.add(new QuestionnaireDto(q) ) );
		return questionnaireDtos;
		
	}
	
	@RequestMapping("/api/questionnaire/{idCanal}")
	public List<QuestionnaireDto> listeQuestionnairesParCanal(@PathVariable int idCanal) {	
		ArrayList<QuestionnaireDto> questionnaireDtos = new ArrayList<QuestionnaireDto>();
		questionnaireService.findQuestionnaireByCanal(idCanal).forEach( q -> questionnaireDtos.add(new QuestionnaireDto(q) ) );
		return questionnaireDtos;
		
	}
	
	@RequestMapping("/api/questionnaire/{idCanal}/{idCreateur}")
	public List<QuestionnaireDto> listeQuestionnairesParCanal(@PathVariable Integer idCanal, @PathVariable Integer idCreateur) {	
		ArrayList<QuestionnaireDto> questionnaireDtos = new ArrayList<QuestionnaireDto>();
		questionnaireService.findQuestionnaireByCanalAndCreateur(idCanal, idCreateur).forEach( q -> questionnaireDtos.add(new QuestionnaireDto(q) ) );
		return questionnaireDtos;
		
	}
	

}
