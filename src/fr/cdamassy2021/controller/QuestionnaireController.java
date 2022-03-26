package fr.cdamassy2021.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.cdamassy2021.entity.Questionnaire;
import fr.cdamassy2021.service.QuestionnaireService;

/**
 * 
 * @author Kamal, Ben, Vinoth, Thomas
 * 
 * */

@Controller
public class QuestionnaireController {
	@Autowired
	private QuestionnaireService questionnaireService;
	
	@RequestMapping("/questionnaire/afficher")
	public ModelAndView afficherQuestionnaires() {
		Collection<Questionnaire> listQuestionnaire = questionnaireService.listAll();
		ModelAndView mav = new ModelAndView("afficherquestionnaires");
		
		mav.addObject("allQuestionnaires", listQuestionnaire);
		return mav;
	}
	
	

}
