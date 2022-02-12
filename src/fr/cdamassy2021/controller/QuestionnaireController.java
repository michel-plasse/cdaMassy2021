package fr.cdamassy2021.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.cdamassy2021.entity.Questionnaire;
import fr.cdamassy2021.service.QuestionnaireService;


/**
 * 
 * @author grp2 11/02/2022
 * 
 * */

@Controller
public class QuestionnaireController {

	@Autowired
	private QuestionnaireService questionnaireService;
	
	@RequestMapping("/questionnaires")
	public ModelAndView afficherQuestionnaires() {
		
		List<Questionnaire> listeQuestionnaires = questionnaireService.listAll();
		ModelAndView mav = new ModelAndView("questionnaires");
		
		mav.addObject("questionnaires", listeQuestionnaires);
		
		return mav;
		
	}
					
}
